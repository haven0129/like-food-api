package com.likefood.pojo.common;

import javax.validation.constraints.NotNull;

public class IdDto {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
