package info.ashutosh.reposetory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import info.ashutosh.model.User;

public interface UserReposetory extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
