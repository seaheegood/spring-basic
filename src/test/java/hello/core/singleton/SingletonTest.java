package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {

        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        
        /**
         * 우리가 만들었던 스프링 없는 순수한 AppConfig 는 요청을 할 때 마다 객체를 새로 생성한다.
         * 고객 트래픽이 초당 100이 나오면 100개 객체가 생성되고 소멸된다. -> 메모리 낭비가 심함
         * 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. -> 싱글톤 패턴
         */
    }



    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1); //singletonService1 = hello.core.singleton.SingletonService@797badd3
        System.out.println("singletonService2 = " + singletonService2); //singletonService2 = hello.core.singleton.SingletonService@797badd3

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        /**
         * 싱글톤 패턴 문제점
         * 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
         * 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP 를 위반한다
         * 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다
         * 테스트하기 어렵다
         * 내부 속성을 변경하거나 초기화 하기 어렵다
         * private 생성자로 자식 클래스를 만들기 어렵다.
         * 결론적으로 유연성이 떨어진다.
         * 안티패턴으로 불리기도 한다,
         */
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤") void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        //참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1); System.out.println("memberService2 = " + memberService2);
        //memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
