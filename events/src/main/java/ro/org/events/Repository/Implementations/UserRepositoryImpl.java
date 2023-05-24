package ro.org.events.Repository.Implementations;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import ro.org.events.Repository.BaseRepository;
import ro.org.events.Repository.Interfaces.UserRepository;
import ro.org.events.Repository.Models.User;
@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public String createUser(String username, String password, boolean is_admin) {
       StoredProcedureQuery query = BaseRepository.getEntityManager()
               .createStoredProcedureQuery("create_user")
                .registerStoredProcedureParameter("username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("password", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_admin", Boolean.class, ParameterMode.IN)
                .registerStoredProcedureParameter("message", String.class, ParameterMode.OUT)
                .setParameter("username", username)
                .setParameter("password", password)
                .setParameter("is_admin", is_admin);

        query.execute();
        return (String) query.getOutputParameterValue("message");
    }

    @Override
    public User getUser_byId(Long id) {
        return null;
    }

    @Override
    public User getUser_byUsername(String username) {
        return null;
    }

    @Override
    public String updateUser(String username, String password, boolean is_admin) {
        return null;
    }

    @Override
    public String deleteUser(String username) {
        return null;
    }
}
