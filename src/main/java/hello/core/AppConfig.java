package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig 의 등장으로 애플리케이션이 크게 사용 영역과, 객체를 생성하고 구성(Configuration) 하는 영역으로 분리
 */
@Configuration
//스프링 컨테이너는 위의 어노테이션이 붙은 AppConfig 를 설정 정보로 사용한다.
public class AppConfig { // AppConfig 는 애플리케이션의 실제 동장엑 필요한 구현 객체를 생성한다.
    // AppConfig 는 생성항 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입(연결) 해준다.

    @Bean // Bean 어노테이션이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다 -> 이렇게 등록된 객체를 스프링 빈이라고 칭함
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        // 생성자 주입
        // 클라이언트인 MemberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입하는 것 같다하여
        // DI(Dependency Injection) = 의존관계 주입 또는 의존성 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        // fix -> rate 로 변경할 때, 사용영역의 어떠한 코드도 건드리지 않음
        return new RateDiscountPolicy();
    }
}
