package com.backend134.salon.services;

import com.backend134.salon.dtos.about.AboutHomeDto;

public interface AboutService {
    AboutHomeDto getAboutForHome(); //Ana sehife ucun (qısaldılmış description ile)
    AboutHomeDto getAboutForPage();

}
