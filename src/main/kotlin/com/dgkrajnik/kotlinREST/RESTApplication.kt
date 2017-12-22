package com.dgkrajnik.kotlinREST

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.security.Principal
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

@SpringBootApplication
@EnableSwagger2
@EnableAspectJAutoProxy
class KotlinRestApplication

fun main(args: Array<String>) {
    var mapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .setDateFormat(SimpleDateFormat("yyyy-MM-dd hh:mm:ss")) // @JsonFormat doesn't deserialise datetimes right.
    SpringApplication.run(KotlinRestApplication::class.java, *args)
}

@RestController
@RequestMapping("hello")
@Api(value="Hello Machine", description="A handful of API endpoints for exercising Spring.")
class SpringHelloController {
    @Inject //Inject is hip and modern.
    private lateinit var springHelloService: HelloService

    val logger: Logger = LoggerFactory.getLogger("HelloLogger")

    @ApiOperation(value="A simple testing endpoint.")
    @GetMapping("/string")
    fun helloString() = "Hello, Spring!"

    @ApiOperation(value="A testing endpoint, but for services.")
    @GetMapping("/service")
    fun helloService() = springHelloService.helloAsAService()

    @ApiOperation(value="An endpoint to exercise HTTP Basic Auth")
    @GetMapping("/secureData", produces=["application/json"])
    fun helloData(): HelloData {
        logger.info("User Accessed secureData.")
        return HelloData("Hello, Data!")
    }

    @ApiOperation(value="A simple endpoint secured by OAuth.")
    @GetMapping("/secureOAuthData", produces=["application/json"])
    fun helloOAuthData(): HelloData {
        logger.info("User accessed secureOAuthData.")
        return HelloData("Hello, OAuth!")
    }

    @ApiOperation(value="An endpoint that will throw an error.")
    @GetMapping("/throwAnError")
    fun badBoy(): Nothing = throw HttpMessageNotReadableException("Wink wonk")

    @ApiOperation(value="A GET endpoint which throws an error if you don't provide it reqparam=22.")
    @GetMapping("/badRequest", produces=["application/json"])
    fun badReq(@RequestParam("reqparam", required=false) requestParam: Int?): ShimData {
        if (requestParam != 22) {
            throw EntityNotFoundException("Entity $requestParam != 22 not found.")
        } else {
            return ShimData("Good Stuff")
        }
    }

    @ApiOperation(value="A POST endpoint which throws an error if you provide it a reqparam > 22.")
    @PostMapping("/badPost", produces=["application/json"])
    fun badPost(@RequestParam("reqparam", required=false) requestParam: Int?): ShimData {
        if (requestParam == null || requestParam > 22) {
            throw ValidationFailedException("request", "reqparam", requestParam, "Value must be <= 22")
        } else {
            return ShimData("I got $requestParam")
        }
    }

    @ApiOperation(value="An endpoint which is explicitly logged on the backend.")
    @GetMapping("/loggedEndpoint", produces=["text/html"])
    fun iAmWatchingYou(request: HttpServletRequest): String {
        // We can explicitly log something at a specific logging level like this.
        logger.info("User ${request.remoteAddr} accessed ${request.requestURL}.")
        return "<div style=\"position:absolute; top:50%; text-align:center; width:100%; transform:translateY(-50%);\">I'm watching you.</div>"
    }

    @Auditable(AuditCode.BEING_WATCHED)
    @ApiOperation(value="An endpoint which is explicitly logged on the backend, but through a different means.")
    @GetMapping("/auditedEndpoint", produces=["text/html"])
    fun heIsWatchingYou(request: HttpServletRequest, principal: Principal?): String {
        return """<div style="position:absolute; top:50%; text-align:center; width:100%; transform:translateY(-50%);">Ḩ̥̭͚͚̼̣̻̂̈̊͛ͫ̽͛̇̏͠Ȩ͓̭̭̱̮͕̐͑ͦ͛͐ͤͩ ̡̩͉̹̯̹̅̇̔į͈̟̰̫̓ͤ̒̊̅̀s̲ͫ͆̄̑ͨ̓͂ ̡̰̋͊́̎̅̐̇͟ͅŵ̭̲̣͉̺ͫͩ͘ą͛̈́͒̂͑͞҉͕͇̟͔̤t̲̳̰͐͆̈́͛͋ͭ͊͒̔c̶͇ͭͥ̆̂͊ͤ̿̋ḧ̗̰̯ͪͪ̀ͩ͑̐͞ͅi̵͇̫̰͗̒͜nͯ̉͋͞͏̲̙͎̠̹̘̦͢g̰̻͈̻̙̰͇͎̓̄̔́̓̔ͫ̔ ̡͉̜̞̟̉͛̓͑̈ͮ̒͢ȳ̢̩͚̼͓̤̐͛̎̈́͛ͨ̊o̯̹͈̟̻͚͒̍ͯͭͪ̂̏ͭͅu̧͈̤̟̟͉̝̟͐̔̅̿̓̇̓̚</div>"""
    }

    @Auditable(AuditCode.BEING_WATCHED)
    @ApiOperation(value="An endpoint which is explicitly logged on the backend, but which errors out.")
    @GetMapping("/erroringAuditedEndpoint", produces=["application/json", "text/html"])
    fun heIsABitIllToday() {
        // We throw an Entity Not Found Exception for no other reason than because we already have a handler, so the log is cleaner.
        throw EntityNotFoundException("Sometheng Happoned")
    }
}

@Service
class HelloService {
    fun helloAsAService() = "Hello, Service!"
}

/**
 * A shim for testing out sending some automatically de/serialised data.
 */
data class HelloData(val message: String)
/**
 * A shim for testing out sending some automatically de/serialised data.
 */
data class ShimData(val jsonshim: String)

@Configuration
class notWorking() : WebMvcConfigurerAdapter() {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        //tyvm harrison
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
