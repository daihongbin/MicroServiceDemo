using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;

namespace WebAppForConsul
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CreateWebHostBuilder(args).Build().Run();
        }

        public static IWebHostBuilder CreateWebHostBuilder(string[] args)
        {
            //从控制台启动行读取ip和端口启动参数
            //var config = new ConfigurationBuilder().AddCommandLine(args).Build();

            //202.135.136.108
            return WebHost.CreateDefaultBuilder(args)
                .UseStartup<Startup>()
                .UseUrls($"http://127.0.0.1:5001");
        }
    }
}
