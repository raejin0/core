package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {



	@Test
	void StatefulServiceSingleton() throws Exception {
	    // given
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);


		/*statefulService1.order("userA", 10000); //ThreadA: B사용자 10000원 주문
		statefulService1.order("userB", 20000); //ThreadA: A사용자 10000원 주문

		int price1 = statefulService1.getPrice();//ThreadA: 사용자B 주문 금액 조회

		assertThat(price1).isEqualTo(20000);*/

		int price1 = statefulService1.order("userA", 10000);
		int price2 = statefulService1.order("userB", 20000);

		assertThat(price1).isEqualTo(10000);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}

}