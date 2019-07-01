using Microsoft.AspNetCore.Mvc;

namespace WebAppForConsul.Controllers
{
    [Produces("application/json")]
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
