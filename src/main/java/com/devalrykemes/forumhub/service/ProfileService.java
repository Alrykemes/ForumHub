package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.profile.Profile;
import com.devalrykemes.forumhub.domain.profile.ProfileRequestDto;
import com.devalrykemes.forumhub.domain.profile.ProfileResponseDto;
import com.devalrykemes.forumhub.domain.profile.ProfileUpdateDto;
import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    public final ProfileRepository profileRepository;
    public final UserService userService;

    public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) {
        Profile newProfile = new Profile(profileRequestDto);
        newProfile.setUserOwner(userService.userforProfileById(profileRequestDto.idUser()));
        newProfile = profileRepository.save(newProfile);
        return new ProfileResponseDto(newProfile);
    }

    public ProfileResponseDto getProfileById(Long id) {
        return new ProfileResponseDto(profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profile not found: " + id)));
    }

    public ProfileResponseDto updateProfile(ProfileUpdateDto profileUpdateDto) {
        this.getProfileById(profileUpdateDto.id());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        if(profileRepository.existsByProfileIdAndCreatorEmail(profileUpdateDto.id(), userEmail)) {
            profileRepository.updateProfileById(profileUpdateDto.id(), profileUpdateDto.name(), profileUpdateDto.idUser());
            return this.getProfileById(profileUpdateDto.id());
        } else {
            throw new IllegalArgumentException("This Profile has not been created by this user!");
        }
    }

    public void deleteById(Long id) {
        this.getProfileById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        if(profileRepository.existsByProfileIdAndCreatorEmail(id, userEmail)) {
            profileRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("This Profile has not been created by this user!");
        }
    }

    public Profile profileById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profile not found: " + id));
    }
}
