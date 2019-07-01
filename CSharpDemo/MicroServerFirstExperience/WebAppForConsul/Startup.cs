using Consul;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Threading.Tasks;

namespace WebAppForConsul
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc().SetCompatibilityVersion(CompatibilityVersion.Version_2_2);
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, IApplicationLifetime lifetime)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseMvc();

            var ip = "127.0.0.1"; //部署到不同服务器的时候不能写成127.0.0.1或者0.0.0.0,因为这是让服务消费者调用的地址
            var port = 5001;
            var serviceName = "Values";
            var serviceId = "test123456789";


            //向consul注册服务
            using (var client = new ConsulClient(ConsulConfig))
            {
                var asr = new AgentServiceRegistration
                {
                    ID = serviceId,
                    Name = serviceName,
                    Address = ip, //我的ip地址(可以被其他应用访问的地址，本地测试可以用127.0.0.1，机房环境中一定要写自己的内网ip地址)
                    Port = port,
                    Check = new AgentServiceCheck
                    {
                        DeregisterCriticalServiceAfter = TimeSpan.FromSeconds(5), //服务停止多久后注册
                        Interval = TimeSpan.FromSeconds(10),//健康检查时间间隔，或者称为心跳间隔
                        HTTP = $"http://{ip}:{port}/api/health",//健康检查地址
                        Timeout = TimeSpan.FromSeconds(5)
                    }
                };

                client.Agent.ServiceRegister(asr).Wait();
            }

            //注销Consul事件
            lifetime.ApplicationStopping.Register(() =>
            {
                using (var client = new ConsulClient(ConsulConfig))
                {
                    Console.WriteLine("应用退出，开始从Consul注销");
                    client.Agent.ServiceDeregister(serviceId).Wait();
                }
            });

        }

        private void ConsulConfig(ConsulClientConfiguration config)
        {
            config.Address = new Uri("http://127.0.0.1:8500");
            config.Datacenter = "dc1";
        }
    }
}
