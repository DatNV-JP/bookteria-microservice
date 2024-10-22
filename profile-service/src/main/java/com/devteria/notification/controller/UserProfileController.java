package com.devteria.notification.controller;

import com.devteria.notification.dto.response.UserProfileResponse;
import com.devteria.notification.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/users/{profileId}")
    UserProfileResponse getProfile(@PathVariable String profileId) {
        return userProfileService.getProfile(profileId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    List<UserProfileResponse> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }
}
