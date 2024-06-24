package com.sitem.demo.core.model.dto;

import com.sitem.demo.core.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
