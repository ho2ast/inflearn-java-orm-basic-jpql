package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUsername("member" + i);
//                member.setAge(i);
//                em.persist(member);
//            }
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.setTeam(team);
            em.persist(member);

            Member member1 = new Member();
            member1.setUsername("member2");
            member1.setAge(10);
            member1.setType(MemberType.ADMIN);
            member1.setTeam(team);
            em.persist(member1);

//            // TypeQeury - 리턴 타입이 명확 할 때
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            // Query - 리턴 타입이 명확 하지 않을 때
//            Query query1 = em.createQuery("select m.username, m.age from Member m");
//
//            // 리턴 값이 둘 이상 일 때
//            List<Member> resultList = query.getResultList();
//            // 리턴 값이 하나 일 때
//            Member singleResult = query.getSingleResult();
//
//            // 파라미터 바인딩
//            TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.username = :username", Member.class);
//            query2.setParameter("username", "member1");
//            Member singleResult1 = query2.getSingleResult();
//
//            // chaining
//            Member singleResult2 = query2.setParameter("username", "member1").getSingleResult();

            // ==== 프로젝션 ====
            em.flush();
            em.clear();
            // 다음의 Member는 영속성 관리가 될까?
//            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
//            Member findMember = result.get(0);
//            findMember.setAge(20); // -> List<Member>는 영속성 관리가 된다!!!
//
//            // 다음은 모두 join해서 Team을 가져오는데 명시적으로 join을 써주는게 좋다
//            List<Team> result2 = em.createQuery("select m.team from Member m", Team.class).getResultList();
//            List<Team> result3 = em.createQuery("select m.team from Member m join m.team", Team.class).getResultList();
//
//            // 임베디드 타입 - Order의 소속되어 있기 때문에 조인 없이 그냥 가져옴
//            List<Order> result4 = em.createQuery("select o.address from Order o", Order.class).getResultList();
//
//            // 스칼라 타입
//            List<Object[]> result5 = em.createQuery("select m.username, m.age from Member m").getResultList();
//            System.out.println("result5.get(0) = " + result5.get(0)[0]);
//            // new 명령어로 조회 (DTO생성 필요)
//            List<MemberDTO> result6 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            // 페이징
            // JPA에서 알아서 DB에 맞게 구체화 해서 쿼리 전송함
//            List<Member> result7 = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();
//            for (Member member1 : result7) {
//                System.out.println("member = " + member1);
//            }

            // Join
            // inner join
//            List<Member> result8 = em.createQuery("select m from Member m inner join m.team", Member.class)
//                    .getResultList();
            // left join
//            List<Member> result9 = em.createQuery("select m from Member m left join m.team", Member.class)
//                    .getResultList();
            // 세타조인 - 막조인이라고 부른다ㅋㅋㅋ
//            List<Member> result10 = em.createQuery("select m from Member m, Team t where m.username = t.name", Member.class)
//                    .getResultList();
            // on 절 (join절은 조건문)
//            List<Member> result11 = em.createQuery("select m from Member m left join Team t on t.name = 'teamA'", Member.class)
//                    .getResultList();


            // 서브쿼리

            // 기본타입
//            Query result11 = em.createQuery("select 'Hello', true from Member m where m.type = jpql.MemberType.MEMBER");
//            for (Object result : result11.getResultList()) {
//                System.out.println("member1 = " + result);
//            }

            // 조건식
//            Query query = em.createQuery("select case when m.age <= 10 then '학생요금' when m.age >= 60 then '경로요금' else '일반요금' end from Member m");
//            List<Object> resultList = query.getResultList();
//            for (Object member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

//            List<String> query1 = em.createQuery("select coalesce(m.username, '이름없는 회원') from Member m").getResultList();
//            List<String> query2 = em.createQuery("select nullif(m.username, '이름없는 회원') from Member m").getResultList();
//            for (String member1 : query1) {
//                System.out.println("member1 = " + member1);
//            }

            // JQPL 기본함수
            // concat
//            List<String> query1 = em.createQuery("select concat('a', 'b') from Member m", String.class)
//                    .getResultList();
//            List<String> query2 = em.createQuery("select 'a' || 'b' from Member m", String.class)
//                    .getResultList();

            // substring
//            List<String> query3 = em.createQuery("select substring(m.username, 2, 3) from Member m", String.class)
//                    .getResultList();

            // locate 문자위치
//            List<Integer> query4 = em.createQuery("select locate('de', 'abcdefg') from Member m", Integer.class)
//                    .getResultList();

            // size 해당 객체의 크기를 반환
//            List<Integer> query5 = em.createQuery("select size(t.members) from Team t", Integer.class)
//                    .getResultList();

            // 사용자 정의 함수사용
            // dialect 등록 -> persistence.xml에서 적용
//            List<String> query6 = em.createQuery("select function('group_concat', m.username) from Member m", String.class)
//                    .getResultList();


            // 경로 표현식
            // .을 찍어 객체 그래프를 탐색하는 것

            // 상태필드 - 경로 탐색의 끝, 탐색X (m.username)
//            List<String> query5 = em.createQuery("select m.username from Member m", String.class)
//                    .getResultList();

            // 단일 값 연관 경로 - 묵시적 내부 조인(조인 쿼리발생) 탐색가능
//            List<String> query6 = em.createQuery("select m.team from Member m", String.class)
//                    .getResultList();

            // 컬렉션 값 연관 경로 - 탐색 불가능
//            List<Collection> query7 = em.createQuery("select t.members from Team t", Collection.class)
//                    .getResultList();
            // 명시적 조인을 통해서 별칭을 얻어서 사용할 수 있다.
//            List<Collection> query8 = em.createQuery("select m from Team t join t.members m", Collection.class)
//                    .getResultList();


            // 페치조인 (fetch join)
            // "select m from Member m"지연 로딩으로 조회시 쿼리가 횟수만큼 조회 될 가능성이 있음 N + 1 묹[
//            List<Member> query9 = em.createQuery("select m from Member m join fetch m.team", Member.class)
//                    .getResultList();
//            for (Member member1 : query9) {
//                System.out.println("member1 = " + member1.getTeam().getName());
//            }

            // 컬렉션 페치 조인
//            List<Team> query10 = em.createQuery("select t from Team t join fetch t.members", Team.class)
//                    .getResultList();
//            for (Team team1 : query10) {
//                System.out.println("team1 = " + team1.getName() + ", " + team1.getMembers().size());
//            }

            // distinct로 중복제거 시도
//            List<Team> query11 = em.createQuery("select distinct t from Team t join fetch t.members", Team.class)
//                    .getResultList();
//            for (Team team1 : query10) {
//                System.out.println("team1 = " + team1.getName() + ", " + team1.getMembers().size());
//            }

            // 페치 조인과 일반 조인의 차이
            // 일반 조인 -  조인을 하나 t만 가져옴 / 페치조인은 그냥 처음부터 모든 데이터 다 조회해옴
//            List<Team> query12 = em.createQuery("select t from Team t join t.members", Team.class)
//                    .getResultList();
//            for (Team team1 : query12) {
//                System.out.println("team1 = " + team1.getName() + ", " + team1.getMembers().size());
//            }

            // 페치 조인 대상에는 별칭을 줄 수 없다.
            // 컬렉션을 페치 조인으로 페이징 하면 안된다. 모든 데이터 다 가져와서 메모리에서 페이징 처리함
            // 컬렉션 일 때 @BatchSize를 이용할 수 있다
            // 컬렉션 대상에서 해도 되고 글로벌 설정으로도 세팅할 수 있다


            // 다형성 쿼리

            // jpql에서 엔티티를 직접 사용하면 sql에서 해당 엔티티의 기본 키 값을 사용
//            List<Member> query13 = em.createQuery("select m from Member m where m = :member", Member.class)
//                    .setParameter("member", member)
//                    .getResultList();
//            List<Member> query14 = em.createQuery("select m from Member m where m.id = :memberId", Member.class)
//                    .setParameter("memberId", member.getId())
//                    .getResultList();

            // 외래 키 값
//            List<Member> query15 = em.createQuery("select m from Member m where m.team = :team", Member.class)
//                    .setParameter("team", team)
//                    .getResultList();

            // NamedQuery - 쿼리를 작성 해놓고 이름 붙여서 사용
            // Spring Data JPA에서는 @Query로 사용
//            List<Member> query15 = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", member.getUsername())
//                    .getResultList();

            // 벌크연산
            // 쿼리 한 번으로 여러 테이블 로우 변경(엔티티)
            // FLUSH 자동 호출(쿼리 나갈 때 자동으로 됨)
            // 벌크연산은 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리
            // 1. 벌크 연산을 먼저 실행 하던지
            // 2. 벌크 연산 수행 후 영속석 컨텍스트 초기화
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            System.out.println("resultCount = " + resultCount);

            // 영속성 컨텍스트에는 반영 안됨.
            System.out.println("member.getAge() = " + member.getAge());
            System.out.println("member1.getAge() = " + member1.getAge());


            // ==== 프로젝션 ====

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
