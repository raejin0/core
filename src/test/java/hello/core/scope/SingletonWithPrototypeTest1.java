package hello.core.scope;

import ch.qos.logback.core.net.server.Client;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Test
	void singletonClientUsagePrototype() {
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean1.logic();
		assertThat(count2).isEqualTo(1);
	}

	@Scope("singleton") // deafult라 안해줘도 됨
//	@RequiredArgsConstructor
	static class ClientBean {

		@Autowired
//		private ObjectProvider<PrototypeBean> prototypeBeanProvider;
		private Provider<PrototypeBean> prototypeBeanProvider;   // javax.inject

		public int logic() {
//			PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
			return prototypeBean.getCount();

		}

/*
		private final PrototypeBean prototypeBean;

		@Autowired // 생략 가능
		public ClientBean(PrototypeBean prototypeBean) {
			this.prototypeBean = prototypeBean;
		}

		public int logic() {
			return prototypeBean.getCount();
		}
*/
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init = " + this);
		}

		@PreDestroy // proptotype scope이라서 호출 안되지만 그냥 써둠
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}


	}
}
