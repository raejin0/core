package hello.core.findBean;

import hello.core.AppConfig;
import hello.core.discount.AmtDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.PctDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
	void findBeanByParentTypeDuplicate() {
//		DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
		assertThrows(NoUniqueBeanDefinitionException.class,
				() -> ac.getBean(DiscountPolicy.class));
	}

	@Test
	@DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
	void findBeanByParentTypeBeanName() {
		DiscountPolicy amtDiscountPolicy = ac.getBean("amtDiscountPolicy", DiscountPolicy.class);
		assertThat(amtDiscountPolicy).isInstanceOf(AmtDiscountPolicy.class);
	}

	@Test
	@DisplayName("특정 하위 타입으로 조회, 좋지 않은 방법이다.")
	void findBeanBySubType() {
		DiscountPolicy amtDiscountPolicy = ac.getBean(AmtDiscountPolicy.class);
		assertThat(amtDiscountPolicy).isInstanceOf(AmtDiscountPolicy.class);
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회하기")
	void findAllByParentType() {
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		assertThat(beansOfType.size()).isEqualTo(2);

		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " object = " + beansOfType.get(key));
		}
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회하기 - Object")
	void findAllBeanByObjectType() {
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
 		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " object = " + beansOfType.get(key));
		}
	}

	@Configuration
	static class TestConfig {

		@Bean
		public DiscountPolicy amtDiscountPolicy() {
			return new AmtDiscountPolicy();
		}

		@Bean
		public DiscountPolicy pctDiscountPolicy() {
			return new PctDiscountPolicy();
		}
	}



}
