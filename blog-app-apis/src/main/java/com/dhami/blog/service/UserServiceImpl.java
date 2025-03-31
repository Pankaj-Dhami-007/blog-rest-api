package com.dhami.blog.service;
import com.dhami.blog.entity.User;
import com.dhami.blog.exceptions.ResourceNotFoundException;
import com.dhami.blog.payloads.UserDTO;
import com.dhami.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User userInDb = userRepository.save(user);
        return this.userToDTO(userInDb);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = this.userRepository.findAll();
        List<UserDTO> userDTOList = users.stream().map(user->this.userToDTO(user)).toList();
        return userDTOList;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User"," Id", id));
        return this.userToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User userInDb = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User"," Id ",id));
        userInDb.setName(userDTO.getName());
        userInDb.setEmail(userDTO.getEmail());
        userInDb.setPassword(userDTO.getPassword());
        userInDb.setAbout(userDTO.getAbout());
        User updatedUser = userRepository.save(userInDb);
        UserDTO userDTO1 = this.userToDTO(updatedUser);
        return userDTO1;
    }

    @Override
    public void deleteUser(Long id) {

        User user = this.userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id",id));
        this.userRepository.delete(user);
    }

//    private User dtoToUser(UserDTO userDTO){
//        User user = new User();
//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(user.getAbout());
//        return user;
//    }

    private User dtoToUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
//    private UserDTO userToDTO(User user){
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
//        return userDTO;
//    }

    private UserDTO userToDTO(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
