using Microsoft.AspNetCore.Mvc;

namespace WebAppForConsul.Controllers
{
    //Consul通过调用这个api来确认服务是否健康
    [Produces("application/json")]
    [Route("api/Health")]
    public class HealthController:Controller
    {
        [HttpGet]
        public IActionResult Get() => Ok("ok");
    }
}
