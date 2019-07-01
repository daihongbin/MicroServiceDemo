using Consul;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Threading.Tasks;
using WebAppForConsul.Extensions;
using WebAppForConsul.Models;

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

            var ip = Configuration["ip"]; //部署到不同服务器的时候不能写成127.0.0.1或者0.0.0.0,因为这是让服务消费者调用的地址
            var port = Convert.ToInt32(Configuration["port"]);
            Console.WriteLine("配置的ip值：" + ip);
            Console.WriteLine("配置的port值：" + port);

            //向consul注册服务
            //ConsulClient client = new ConsulClient(ccf => 
            //{
            //    ccf.Address = new Uri("http://127.0.0.1:8500");
            //    ccf.Datacenter = "dc1";
            //});

            //Task<WriteResult> result = client.Agent.ServiceRegister(new AgentServiceRegistration
            //{
            //    ID = "apiservice1" + Guid.NewGuid().ToString("N"), //服务编号，不能重复
            //    Name = "apiservice1",//服务的名字
            //    Address = ip, //我的ip地址(可以被其他应用访问的地址，本地测试可以用127.0.0.1，机房环境中一定要写自己的内网ip地址)
            //    Port = port,
            //    Check = new AgentServiceCheck
            //    {
            //        DeregisterCriticalServiceAfter = TimeSpan.FromSeconds(5), //服务停止多久后注册
            //        Interval = TimeSpan.FromSeconds(10),//健康检查时间间隔，或者称为心跳间隔
            //        HTTP = $"http://{ip}:{port}/api/health",//健康检查地址
            //        Timeout = TimeSpan.FromSeconds(5)
            //    }
            //});

            var consulService = new ConsulService
            {
                IP = ip,
                Port = 8500
            };

            var healthService = new HealthService
            {
                IP = ip,
                Port = port,
                Name = "WebAppForConsul"
            };

            app.RegisterConsul(lifetime,healthService, consulService);
        }
    }
}
