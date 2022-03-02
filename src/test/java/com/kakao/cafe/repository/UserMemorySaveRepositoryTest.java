package com.kakao.cafe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.entity.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserMemorySaveRepositoryTest {

    private UserMemorySaveRepository userRepository;
    private User userA, userB;

    @BeforeEach
    void testSetUp() {
        userRepository = new UserMemorySaveRepository();
        userA = new User("emailA", "userIdA", "nameA", "pawA");
        userB = new User("emailB", "userIdB", "nameB", "pawB");
    }

    @AfterEach
    void testAfterEach() {
        userRepository.clearStore();
    }

    @DisplayName("유저가 정상적으로 저장소에 저장되면 저장된 유저를 반환해야 한다.")
    @Test
    void userSave() {
        // when
        User saveUser = userRepository.userSave(userA);
        // then
        assertThat(userA).isEqualTo(saveUser);
    }

    @DisplayName("유저 저장소에 일치하는 UserId가 있는 경우 true를 반환해야 한다.")
    @Test
    void findUserId() {
        // when
        User saveUserA = userRepository.userSave(userA);
        User saveUserB = userRepository.userSave(userB);
        // then
        assertThat(saveUserA.isSameUserId(userA.getUserId())).isTrue();
        assertThat(saveUserB.isSameUserId(userB.getUserId())).isTrue();
    }

    @DisplayName("유저 저장소에 일치하는 UserEmail이 있는 경우 true를 반환해야 한다.")
    @Test
    void findEmail() {
        // when
        User saveUserA = userRepository.userSave(userA);
        User saveUserB = userRepository.userSave(userB);
        // then
        assertThat(saveUserA.isSameUserEmail(userA.getEmail())).isTrue();
        assertThat(saveUserB.isSameUserEmail(userB.getEmail())).isTrue();
    }

    @DisplayName("유저 저장소에 저장되어 있는 유저 수를 반환해야 한다.")
    @Test
    void findAllUser() {
        // given
        int userCount = 2;
        // when
        userRepository.userSave(userA);
        userRepository.userSave(userB);
        // then
        assertThat(userCount).isEqualTo(userRepository.findAllUser().size());
    }
}