package com.eshop.admin.user;
import com.eshop.admin.user.repositories.UserRepository;
import com.eshop.common.entity.User;
import com.eshop.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userNamHM = new User("nam@codejava.net", "nam2020", "Nam", "Ha Minh");
        userNamHM.addRole(roleAdmin);

        User savedUser = userRepository.save(userNamHM);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User userRavi = new User("ravi@gmail.com", "ravi2020", "Ravi", "Kumar");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        userRavi.addRole(roleEditor);
        userRavi.addRole(roleAssistant);

        User savedUser = userRepository.save(userRavi);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = userRepository.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById() {
        User userNam = userRepository.findById(1).get();
        System.out.println(userNam);
        assertThat(userNam).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User userNam = userRepository.findById(1).get();
        userNam.setEnabled(true);
        userNam.setEmail("namjavaprogrammer@gmail.com");

        userRepository.save(userNam);
    }

    @Test
    public void testUpdateUserRoles() {
        User userRavi = userRepository.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);

        userRavi.getRoles().remove(roleEditor);
        userRavi.addRole(roleSalesperson);

        userRepository.save(userRavi);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 2;
        userRepository.deleteById(userId);
        userRepository.findById(userId);
    }
}
