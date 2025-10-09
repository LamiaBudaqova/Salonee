package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminTestimonialTextUpdateDto;

public interface AdminTestimonialTextService {
    AdminTestimonialTextUpdateDto getText();
    void update(AdminTestimonialTextUpdateDto dto);
}
