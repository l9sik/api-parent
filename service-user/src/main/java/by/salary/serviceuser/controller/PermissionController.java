package by.salary.serviceuser.controller;

import by.salary.serviceuser.entities.Permission;
import by.salary.serviceuser.model.premission.PermissionResponseDTO;
import by.salary.serviceuser.model.user.UserResponseDTO;
import by.salary.serviceuser.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;

    }


    @GetMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PermissionResponseDTO> getUserPermissions(@PathVariable BigInteger user_id) {
        return permissionService.getUserPermissions(user_id);
    }

    @GetMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    public String getUserPermissions(@PathVariable String email) {
        return permissionService.getUserPermissions(email).stream().map(PermissionResponseDTO::getName).reduce((a, b) -> a + ", " + b).get();
    }

    @GetMapping("/users/{permission_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> getAllUsersWithPermission(@PathVariable BigInteger permission_id) {
        return permissionService.getAllUsersWithPermission(permission_id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PermissionResponseDTO createPermission(@RequestBody PermissionResponseDTO permissionResponseDTO) {
        return permissionService.createPermission(permissionResponseDTO);
    }

    @PostMapping("/{user_id}/{permission_id}")
    @ResponseStatus(HttpStatus.OK)
    public PermissionResponseDTO addUserPermission(@PathVariable BigInteger user_id,
                                                   @PathVariable BigInteger permission_id,
                                                   @RequestAttribute String email,
                                                   @RequestAttribute List<Permission> permissions) {
        return permissionService.addUserPermission(user_id, permission_id, email, permissions);
    }

    @DeleteMapping("/{user_id}/{permission_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserPermission(@PathVariable BigInteger user_id,
                                     @PathVariable BigInteger permission_id,
                                     @RequestAttribute String email,
                                     @RequestAttribute List<Permission> permissions) {
        permissionService.deleteUserPermission(user_id, permission_id, email, permissions);
    }



}
