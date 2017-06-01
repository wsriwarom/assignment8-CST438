
package hangman.data;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
        
public class WordDataBase {
   
    public static WordGetter selectWord(Integer id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM Word u " +
                "WHERE u.id = :id";
        TypedQuery<WordGetter> q = em.createQuery(qString, WordGetter.class);
        q.setParameter("id", id);
        try {
            WordGetter word = q.getSingleResult();
            return word;
        }catch (NoResultException e){
            return null;
        }finally {
            em.close();
        }
    }
}
