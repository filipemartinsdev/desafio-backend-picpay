package com.desafiopicpay.dto;

import com.desafiopicpay.entity.User;
import com.desafiopicpay.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedUsersResponseDTO {
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
    private Boolean isLast;
    private List<UserResponseDTO> content;

    public PagedUsersResponseDTO(Page<User> page){
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.isLast = page.isLast();
        this.content = page.getContent().stream().map(UserMapper::toUserResponse).toList();
    }
}
