import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * Security configuration is defined with application properties thanks to the following starters:
 * <ul>
 * <li><a href="https://github.com/ch4mpy/spring-addons/tree/master/webmvc/spring-addons-webmvc-client">spring-addons-webmvc-jwt-client</a> which defines a
 * "client" security filter-chain (sessions, CSRF protection, OAuth2 login &amp; logout, redirect unauthorized requests to login with 302) with high &#64;Order
 * and security-matchers defined with com.c4-soft.springaddons.oidc.client.security-matchers property</li>
 * <li><a href=
 * "https://github.com/ch4mpy/spring-addons/tree/master/webmvc/spring-addons-webmvc-jwt-resource-server">spring-addons-webmvc-jwt-resource-server</a> which
 * defines a "resource server" security filter-chain (no session, no CSRF protection, no login nor logout, returns 401 for unauthorized requests) with lowest
 * &#64;Order and no security-matchers (processes all requests that were not processed by other security filter-chains)</li>
 * </ul>
 *
 * @author Jerome Wacongne ch4mp&#64;c4-soft.com
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
}
