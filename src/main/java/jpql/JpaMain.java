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
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            // TypeQeury - 리턴 타입이 명확 할 때
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            // Query - 리턴 타입이 명확 하지 않을 때
            Query query1 = em.createQuery("select m.username, m.age from Member m");

            // 리턴 값이 둘 이상 일 때
            List<Member> resultList = query.getResultList();
            // 리턴 값이 하나 일 때
            Member singleResult = query.getSingleResult();

            // 파라미터 바인딩
            TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query2.setParameter("username", "member1");
            Member singleResult1 = query2.getSingleResult();

            // chaining
            Member singleResult2 = query2.setParameter("username", "member1").getSingleResult();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
