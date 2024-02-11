``## SpringSecurity大致结构
### 过滤器链的概念
#### Architecture
`Client(浏览器)--> Filter --> DelegatingFilterProxy(FilterChainProxy(SecurityFilterChain(SecurityFilter))) --> Filter --> Servlet`  
`DelegatingFilterProxy`,将`Servlet`容器的生命周期和`Spring`的`ApplicationContext`  
桥接在一起,`Servlet`容器允许注册使用它的标准的`Filter`,但它无法感知`Spring`定义的`Bean`。  
`DelegatingFilterProxy`能通过标准`Servlet`容器机制注册，但是将所有的工作代理给一个实现了`Filter`的`Spring Bean`.  

`DelegatingFilterProxy`从`ApplicationContext`获取`BeanFilter`然后调用`Bean Filter`

`FilterChainProxy`是一个由Spring Security提供的特殊的`Filter`，它可以通过`SecurityFilterChain`  
代理大量的`Filter`,由于`FilterChainProxy`是一个`Beean`,它被封装在`DelegatingFilterProxy`中。  

`SecurityFilterChain`  
被`FilterChainProxy`用于决定本次请求哪些`Filter`可以被调用。在SecurityFilterChain`Security Filters`是典型的`Beans`,  
但是他们注册在`FilterChainProxy`而不是在`DelegatingFilterProxy`当中。  
`FilterChainProxy`提供了多种直接注册到`Servlet`容器或`DelegatingFilterProxy`的优势。首先，它给所有`Spring Security`的`Servlet`支持  
提供了一个起始点。因为这个原因，如果你想尝试去定位`Spring Security`的 `Servlet`支持，在`FilterChainProxy`当中添加debug点是一个好的开始的点。  

第二,由于`FilterChainProxy`是`Spring Security`使用的核心,它可以执行不被视为可选的任务。举个例子，它清理`SecurityContext`避免内存泄漏的问题.  
它也支持`SpringSecurity`的`HttpFirewall`去保护应用，对抗特定的攻击。  

额外的，它提供了更灵活的决策方式当一个`SecurityFilterChain`应该被调用时。在一个`Servlet`容器中，`Filter`们被调用取决于URL。
无论如何,`FilterChainProxy`通过`RequestMatcher`接口基于任何在`HttpServletRequest`中的内容来决定调用。  

事实上,`FilterChainProxy`可以被用来决定哪一个`SecurityFilterChain`应该被调用。这允许你应用的不同部分提供分离的配置。  
在多个`SecurityFilterChain`图中`FilterChainProxy`决定哪一个`SecurityFilterChain`应该被调用。只有第一个匹配的`SecurityFilterChain`将会被调用。  
如果一个URL`/api/messages/`被请求，它会首先匹配`SecurityFilterChain0`的`/api/**`部分，所以只有`SecurityFilterChain0`将会被调用，就算它也能匹配上  
`SecurityFilterChainn`.如果一个URL`/message/`被请求，它将不会`SecurityFilterChain0`的`/api/**`部分，所以`FilterChainProxy`将会继续尝试每一个`SecurityFilterChain`.  
除非这里没有其他的.  
注意到`SecurityFilterChain0`只有三个防护`Filter`实例配置。无论如何，`SecurityFilterChainN`有4个`Filter`配置。每一个`SecuirtyFilterChain`可以是独立和单独配置的。  
事实上,一个`SecurityFilterChain`可能会拥有零个security`Filter`，如果应用想要忽略特定的请求。  
#### SecurityFilters
`SecurityFilters`通过`SecurityFilterChain`API添加到`FilterChainProxy`中。`Filter`的顺序很重要。  
如下是一个全面的`SpringSecurity Filter`的顺序。:  

* ForceEagerSessionCreationFilter
* ChannelProcessingFilter
* WebAsyncManagerIntegrationFilter
* SecurityContextPersistenceFilter
* HeaderWriterFilter
* CorsFilter
* CsrfFilter
* LogoutFilter
* OAuth2AuthorizationRequestRedirectFilter
* Saml2WebSsoAuthenticationRequestFilter
* X509AuthenticationFilter
* AbstractPreAuthenticatedProcessingFilter
* CasAuthenticationFilter
* OAuth2LoginAuthenticationFilter
* Saml2WebSsoAuthenticationFilter
* UsernamePasswordAuthenticationFilter
* OpenIDAuthenticationFilter
* DefaultLoginPageGeneratingFilter
* DefaultLogoutPageGeneratingFilter
* ConcurrentSessionFilter
* DigestAuthenticationFilter
* BearerTokenAuthenticationFilter
* BasicAuthenticationFilter
* RequestCacheAwareFilter
* SecurityContextHolderAwareRequestFilter
* JaasApiIntegrationFilter
* RememberMeAuthenticationFilter
* AnonymousAuthenticationFilter
* OAuth2AuthorizationCodeGrantFilter
* SessionManagementFilter
* ExceptionTranslationFilter
* FilterSecurityInterceptor
* SwitchUserFilter

#### SpringSecurity的过滤器链的初始化过程
根据`@EnableWebSecurity`注解,其加载`WebSecurityConfiguration`类,`HttpSecurityConfiguration`类,完成了过滤器链最主要的内容加载。  

`WebSecurityConfiguration#setFilterChainProxySecurityConfigurer()`设置`WebSecurity`对象  
`WebSecurity`类型是用于构建`FilterChainProxy`的工具类.  

`HttpSecurityConfiguration#httpSecurity()`创建`HttpSecurity`构建工具类  
`HttpSecurity`类型是用于构建`SecurityFilterChain`类型的工具类。  

`HttpSecurity`调用继承自`AbstractSecurityBuilder<O>`抽象类的`build()`方法,`build()`方法调用`AbstractSecurityBuilder<O>`抽象类的`doBuild`方法,实际是调用的继承自`AbstractConfiguredSecurityBuilder<O, B extends SecurityBuilder<O>>()`类型的`doBuild()`方法。  
`doBuild()`方法调用`init()`方法,调用`configure()`方法,`configure()`方法向`httpSecurity`当中添加要加入到`SecurityFilterChain`类型的`Filter`类,执行`HttpSecurity`重载的`performBuild()`方法,生成`DefaultSecurityFilterChain`类型作为真正的`SecurityFilterChain`类型对象。  
并加入到`FilterChainProxy`类型中。  
上述流程中`configure()`方法真正意义上在创建过滤器链中需要使用到的过滤器(`Filter`)对象.  

当在自己的`SpringSecurity`配置类当中配置自己的访问规则时,`.cros()`,等同于向过滤器链中添加`Filter`配置.等到`httpSecurity.build()`执行时，会将该`Filter`添加到`SecurityFilterChain`中。

`WebSecurityConfiguration`中使用获取到的`WebSecurity`执行FilterChainProxy的构建,同时将上述构建好的`SecurityFilterChain`对象添加以匿名SecurityBuilder构建类的方式添加到`WebSecurity`中。最终在`WebSecurity`的`performBuild()`将`FilterChainProxy`构建完成  

#### SpringSecurity的方法级防护
从2.0版本起(onward)SpringSecurity大大提高了添加对于你Service层的方法防护。它提供了JSR-250防护注解作为框架的原生`@Secured`注解。  
从3.0起，你也可以使用新的基于表达式的注解。你可以使用`intercept-methods`元素修饰bean声明，或者你可以通过AspectJ风格的切入点来对service层的多个bean进行防护。  

在SpringSecurity5.6,我们可以使用基于注解的防护通过`@EnableMethodSecurity`在一个`@Configuration`实例上。  
这在`@EnableGlobalMethodSecurity`的基础上进行了改进。  
1. 使用简化的`AuthorizationManager`API代替元数据源，配置参数，决策管理器(`decision managers`)，以及`voters`.这简化了重用和自定义。
2. 直接基于bean的配置，代替了要求扩展`GlobalMethodSecurityConfiguration`来进行自定义`bean`.
3. 通过原生Spring AOP进行构建，剥离抽象性以及允许你使用Spring AOP构建块去自定义。
4. 检查注解冲突确保一个明确(unambiguous)的安全配置.
5. 适配JSR-250
6. 支持`@PreAuthorize`、`@PostAuthorize`、`@PreFilter`、以及`@PostFilter`，默认。早期版本阅读`@EnableGlobalMethodSecurity`[https://docs.spring.io/spring-security/reference/5.7/servlet/authorization/method-security.html#jc-enable-global-method-security]  

举个例子,下面的将会启用SpringSecurity的`@PreAuthorize`注解
```java
@EnableMethodSecurity
public class MethodSecurityConfig {
	// ...
}
```
添加一个注解到方法(类或者接口)将会限制对于该方法的访问。Spring Security原生的注解支持为一个方法定义一系列的属性。它们将会被传入到`DefaultAuthorizationMethodInterceptorChain`中去做真正的决策。
```java
    public interface BankService {
    @PreAuthorize("hasRole('USER')")
    Account readAccount(Long id);

    @PreAuthorize("hasRole('USER')")
    List<Account> findAccounts();

    @PreAuthorize("hasRole('TELLER')")
    Account post(Account account, Double amount);
}
```
你可以像如下使用Spring Security的`@Secured`注解:
```java
@EnableMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig {
    // ...
}
```
或者JSR-250的:
```java
@EnableMethodSecurity(jsr250Enabled = true)
public class MethodSecurityConfig {
	// ...
}
```
#### 自定义授权(Customizing Authorization)
`SpringSecurity`的`@PreAuthorize`,`@PostAuthorize`,`@PreFilter`,`@PostFilter`支持丰富的表达式。  
如果你需要自定义表达式被处理的方式，你可以暴露一个自定义的`MethodSecurityExpressionHandler`,像是:  
```java
@Bean
static MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
	DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
	handler.setTrustResolver(myCustomTrustResolver);
	return handler;
}
```
注意:我们暴露`MethodSecurityExpressionHandler`使用`static`方法，来确保Spring在初始化`Spring Security`的方法防护的`@Configuration`类之前发布它。  
同时，对于基于角色的授权，SpringSecurity添加了默认的`ROLE_`前缀，当评估表达式类似于`hasRole`时。  
你可以配置授权规则使用不同的前缀通过暴露`GrantedAuthorityDefaults`bean,就像:  
```java
@Bean
static GrantedAuthorityDefaults grantedAuthorityDefaults() {
    return new GrantedAuthorityDefaults("MYPREFIX_");
}
```
注意:注意:我们暴露`GrantedAuthorityDefaults`使用`static`方法，来确保Spring在初始化`Spring Security`的方法防护的`@Configuration`类之前发布它。  
#### 自定义授权管理器
方法授权是一个执行前和执行后的方法授权组合。  
执行前授权是在方法调用前执行。如果授权拒绝方法，方法不会被调用，同时一个`AccessDeniedException`将被抛出,  
执行后授权在方法调用后执行，但是在方法返回给调用者之前。如果授权拒绝方法，那么值将不会被返回，一个`AccessDeniedException`将被抛出.  
为了重新构建`@EnableMethodSecurity`默认做的一切，你可以使用如下的配置:
```java
@EnableMethodSecurity(prePostEnabled = false)
class MethodSecurityConfig {
	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	Advisor preFilterAuthorizationMethodInterceptor() {
		return new PreFilterAuthorizationMethodInterceptor();
	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	Advisor preAuthorizeAuthorizationMethodInterceptor() {
		return AuthorizationManagerBeforeMethodInterceptor.preAuthorize();
	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	Advisor postAuthorizeAuthorizationMethodInterceptor() {
		return AuthorizationManagerAfterMethodInterceptor.postAuthorize();
	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	Advisor postFilterAuthorizationMethodInterceptor() {
		return new PostFilterAuthorizationMethodInterceptor();
	}
}
```
注意`SpringSecurity`的方法防护基于`Spring AOP`构建。所以拦截器被调用的顺序基于指定的顺序。这个顺序可以通过拦截器(interceptor)实例调用`setOrder`进行自定义。
```java
@Bean
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
Advisor postFilterAuthorizationMethodInterceptor() {
	PostFilterAuthorizationMethodInterceptor interceptor = new PostFilterAuthorizationMethodInterceptor();
	interceptor.setOrder(AuthorizationInterceptorOrders.POST_AUTHORIZE.getOrder() - 1);
	return interceptor;
}
```
你可能在你的应用中只使用`@PreAuthorize`，这种情况下你可能只想用如下:  
```java
@EnableMethodSecurity(prePostEnabled = false)
class MethodSecurityConfig {
	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	Advisor preAuthorize() {
		return AuthorizationManagerBeforeMethodInterceptor.preAuthorize();
	}
}
```
或者，你可能有一个自定义的执行前`AuthorizationManager`想要添加到列表中.  

在这个情况下，你需要告诉SpringSecurity你的`AuthorizationManager`和你的`AuthorizationManager`可应用的方法和类型。
```java
@EnableMethodSecurity
class MethodSecurityConfig {
	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public Advisor customAuthorize() {
		JdkRegexpMethodPointcut pattern = new JdkRegexpMethodPointcut();
		pattern.setPattern("org.mycompany.myapp.service.*");
		AuthorizationManager<MethodInvocation> rule = AuthorityAuthorizationManager.isAuthenticated();
		AuthorizationManagerBeforeMethodInterceptor interceptor = new AuthorizationManagerBeforeMethodInterceptor(pattern, rule);
		interceptor.setOrder(AuthorizationInterceptorsOrder.PRE_AUTHORIZE_ADVISOR_ORDER.getOrder() + 1);
		return interceptor;
    }
}
```
注意:你可以将你的拦截器置入到SpringSecurity的方法拦截器中间，通过在`AuthorizationInterceptorsOrder`中指定的常量。  
同样的对于执行后授权。执行后授权通常考虑分析返回值来验证访问。  
举个例子，你可能有一个用于确认请求的账号是否属于登录的用户的方法,类似于:  
```java
public interface BankService {

	@PreAuthorize("hasRole('USER')")
	@PostAuthorize("returnObject.owner == authentication.name")
	Account readAccount(Long id);
}
```
你可以提供你自己的`AuthorizationMethodInterceptor`来自定义对于返回值的方法是如何评估的。  
举个例子,如果你有自己自定义的注解，你可以将它按如下配置:  
```java
@EnableMethodSecurity
class MethodSecurityConfig {
	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public Advisor customAuthorize(AuthorizationManager<MethodInvocationResult> rules) {
		AnnotationMatchingPointcut pattern = new AnnotationMatchingPointcut(MySecurityAnnotation.class);
		AuthorizationManagerAfterMethodInterceptor interceptor = new AuthorizationManagerAfterMethodInterceptor(pattern, rules);
		interceptor.setOrder(AuthorizationInterceptorsOrder.POST_AUTHORIZE_ADVISOR_ORDER.getOrder() + 1);
		return interceptor;
	}
}
```
同时他将在`@PostAuthorize`拦截器之后被调用。