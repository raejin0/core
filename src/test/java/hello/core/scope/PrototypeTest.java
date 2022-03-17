package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

	@Test
	public void prototypeBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

		System.out.println("find prototypeBean1");
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		System.out.println("find prototypeBean2");
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		System.out.println("prototypeBean1 = " + prototypeBean1);
		System.out.println("prototypeBean2 = " + prototypeBean2);
		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);


		ac.close(); // 종료 메서드가 호출되지 않기 때문에 원할 경우 아래와 같이 직접 호출해야함
//		prototypeBean1.destroy();
//		prototypeBean2.destroy();


	}

	@Scope("prototype")
	static class PrototypeBean {

		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destory");
		}
	}
}
