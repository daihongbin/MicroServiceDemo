using Microsoft.AspNetCore.Mvc;

namespace WebAppForConsul.Controllers
{
    [Route("api/Health")]
    public class HealthController:Controller
    {

        [HttpGet]
        public IActionResult Get()
        {
            return Ok("ok");
        }
    }
}
