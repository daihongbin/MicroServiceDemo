using Consul;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebAppForConsul.Models;

namespace WebAppForConsul.Extensions
{
    public static class ConsulBuilderExtensions
    {

        //Consul服务注册
        public static IApplicationBuilder RegisterConsul(this IApplicationBuilder app, IApplicationLifetime lifetime,
            HealthService healthService, ConsulService consulService)
        {
            var consulClient = new ConsulClient(x => x.Address = new Uri($"http://{consulService.IP}:{consulService.Port}"));
            var registration = new AgentServiceRegistration
            {
                Check = new AgentServiceCheck
                {
                    DeregisterCriticalServiceAfter = TimeSpan.FromSeconds(5), //服务停止多久后注册
                    Interval = TimeSpan.FromSeconds(10),//健康检查时间间隔，或者称为心跳间隔
                    HTTP = $"http://{healthService.IP}:{healthService.Port}/api/health",//健康检查地址
                    Timeout = TimeSpan.FromSeconds(5)
                },
                ID = $"{healthService.Name}_{healthService.Port}",
                Name = healthService.Name,
                Address = healthService.IP,
                Port = healthService.Port,
                Tags = new[] { $"urlprefix-/{healthService.Name}" }
            };

            consulClient.Agent.ServiceRegister(registration).Wait();

            lifetime.ApplicationStopping.Register(() => 
            {
                consulClient.Agent.ServiceDeregister(registration.ID).Wait();
            });
            return app;
        }
    }
}
