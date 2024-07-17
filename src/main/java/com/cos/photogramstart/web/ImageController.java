package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.handler.ex.CustomVaildationException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }

//    @GetMapping("/image/popular")
//    public String popular(Model model) {
//        List<Image> images = imageService.인기사진();
//        model.addAttribute("images", images);
//
//        return "/image/popular";
//    }
    @GetMapping("/image/popular")
    public String popular(Model model) {
        List<Image> images = imageService.인기사진();

        // 각 이미지에 대해 S3 URL을 가져와 설정
        for (Image image : images) {
            // 이미 URL을 저장하고 있으므로 추가적인 변환이 필요하지 않음
        }

        model.addAttribute("images", images);
        return "/image/popular";
    }


    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(@RequestParam("file") MultipartFile file,
                              @RequestParam("caption") String caption,
                              @AuthenticationPrincipal PrincipalDetails principalDetails,
                              Model model) {
        if (file.isEmpty()) {
            throw new CustomValidationApiException("이미지가 첨부되지 않았습니다.", null);
        }

        ImageUploadDto imageUploadDto = new ImageUploadDto();
        imageUploadDto.setFile(file);
        imageUploadDto.setCaption(caption);

        String imageUrl = imageService.사진업로드(imageUploadDto, principalDetails);

        // You can now use imageUrl to set in the model or redirect to a page where it is displayed
        model.addAttribute("imageUrl", imageUrl);

        return "redirect:/user/" + principalDetails.getUser().getId();
    }


}