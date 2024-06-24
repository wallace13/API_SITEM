package sitem.sitem.model.dto;

import sitem.sitem.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
