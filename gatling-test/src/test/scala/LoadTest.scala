import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * init -> getEpg
  */
class LoadTest extends Simulation {


  val httpConf = http
    .baseUrl("http://localhost:8082/CustomerEvidence/api/") // Here is the root for all relative URLs
    .acceptHeader("application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("LoadTest")
    .exec(http("generateProductsAndCustomers")
    .post("users/generateProductsAndCustomers/75"))
    .pause(2)
    .exec(http("generateOrders")
      .post("users/generateOrders/75"))


  setUp(
    scn.inject(atOnceUsers(100))
      .protocols(httpConf))

}


