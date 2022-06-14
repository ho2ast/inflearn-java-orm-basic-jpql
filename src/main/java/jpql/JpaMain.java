package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }

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
            List<Member> result7 = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member1 : result7) {
                System.out.println("member = " + member1);
            }


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
