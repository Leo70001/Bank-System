package com.lkamdem.User;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String userId, String email, String password) {
        User newUser = userRepository.findById(userId);
        if(newUser != null){
            User newUser2 = userRepository.findByEmail(email);
            if(newUser2 == null){
                newUser.setInitialPassword(password); // TODO: Once security completed encrypt password
                newUser.setUserEmail(email);
                userRepository.updateProfile(newUser);
                userRepository.updatePassword(userId, password);
                return newUser;
            }
            return null;

        }
        return null;
    }

    @Override
    public User login(String userId, String password) {
        return null;
    }

    @Override
    public boolean logout(String userId) {
        return false;
    }

    @Override
    public User findUserById(String userId) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public String createUser(String name, String email, String telephone) {
        return "";
    }

    @Override
    public boolean closeUser(String userId) {
        return false;
    }

    @Override
    public boolean unlockUser(String userId) {
        return false;
    }
}
