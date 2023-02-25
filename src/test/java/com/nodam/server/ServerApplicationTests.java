//package com.nodam.server;
//
//import com.nodam.server.dto.LoginDTO;
//import com.nodam.server.dto.MatchDTO;
//import com.nodam.server.dto.UserDTO;
//import com.nodam.server.repository.UserRepository;
//import com.nodam.server.service.AuthService;
//import com.nodam.server.service.MatchService;
//import com.nodam.server.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class ServerApplicationTests {
//
//    @Autowired
//    private MatchService matchService;
//
//    @Autowired
//    private AuthService authService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    public void testGetAllUsers() throws Exception {
//        // GIVEN
//        ArrayList<LoginDTO> arrayList = new ArrayList<>();
//
//        arrayList.add(new LoginDTO("18010704", "19990808"));
//        arrayList.add(new LoginDTO("19010667", "wjdwnstn1919"));
//        arrayList.add(new LoginDTO("18010764", "lina7432429"));
//        arrayList.add(new LoginDTO("18010784", "ghdekdms98@"));
//        for (LoginDTO loginDTO : arrayList){
//            authService.createRefreshToken(loginDTO);
//            UserDTO userDTO = userRepository.getUserById(loginDTO.getId());
//            matchService.match(userDTO.getId(), new MatchDTO("MALE", "01054447857", "GET_JUNIOR", "ALL", 4, 10, "ALL"));
//        }
//
//        // WHEN
//        arrayList.add(new LoginDTO("21011796", "s83188318!"));
//        UserDTO userDTO = userRepository.getUserById("21011796");
//        ArrayList<UserDTO> arrayList1 = matchService.getUsersFitConditions(userDTO, "MALE", "GET_SENIOR", "ALL", 10, 4);
//        ArrayList<UserDTO> arrayList2 = matchService.getUsersFitConditions(userDTO, "MALE", "GET_SENIOR", "ALL", 10, 2);
//        ArrayList<UserDTO> arrayList3 = matchService.getUsersFitConditions(userDTO, "MALE", "GET_SENIOR", "MALE", 10, 4);
//
//
//        // THEN
//        assertEquals(4, arrayList1.size());
//        assertEquals(1, arrayList1.size());
//        assertEquals(2, arrayList1.size());
//
//
//    }
//
//}
