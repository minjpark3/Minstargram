package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cos.photogramstart.domain.image.Image;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;


@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional(readOnly = true)
    public List<Image> 인기사진() {
        return imageRepository.mPopular();
    }

    @Transactional(readOnly = true)
    public Page<Image> 이미지스토리(int principalId, Pageable pageable) {
        Page<Image> images = imageRepository.mStroy(principalId, pageable);

        images.forEach((image) -> {
            image.setLikeCount(image.getLikes().size());
            image.getLikes().forEach(likes -> {
                if (likes.getUser().getId() == principalId) {
                    image.setLikeState(true);
                }
            });
        });

        return images;
    }

    @Transactional
    public String 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();

        try (InputStream inputStream = imageUploadDto.getFile().getInputStream()) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(imageUploadDto.getFile().getSize());
            amazonS3.putObject(bucket, imageFileName, inputStream, metadata);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload file to S3", e);
        }

        String imageUrl = amazonS3.getUrl(bucket, imageFileName).toString();

        Image image = imageUploadDto.toEntity(imageUrl, principalDetails.getUser());
        imageRepository.save(image);

        return imageUrl;
    }

}

//    @Transactional
//    public String 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
//        UUID uuid = UUID.randomUUID();
//        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
//
//        try (InputStream inputStream = imageUploadDto.getFile().getInputStream()) {
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentLength(imageUploadDto.getFile().getSize());
//            amazonS3.putObject(bucket, imageFileName, inputStream, metadata);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to upload file to S3", e);
//        }
//
//        String imageUrl = amazonS3.getUrl(bucket, imageFileName).toString();
//        Image image = imageUploadDto.toEntity(imageUrl, principalDetails.getUser());
//        imageRepository.save(image);
//
//        return imageUrl;
//    }
//}


//@RequiredArgsConstructor
//@Service
//public class ImageService {
//
//    private final ImageRepository imageRepository;
//
//    @Transactional(readOnly = true)
//    public List<Image>인기사진(){
//        return imageRepository.mPopular();
//    }
//
//    @Transactional(readOnly = true)//영속성 컨텍스트변경 감지해서 ,더티체킹, flush반영
//    public Page<Image> 이미지스토리(int principalId, Pageable pageable){
//        Page<Image>images = imageRepository.mStroy(principalId, pageable);
//
//        //images에 좋아요 상태 담기
//        images.forEach((image) -> {
//
//            image.setLikeCount(image.getLikes().size());
//            image.getLikes().forEach(likes -> {
//                if(likes.getUser().getId()==principalId){//해당이미지에 좋아요한 사람들을 찾아서 현재로그인한 사람이 좋아요 한것인지 비교
//                    image.setLikeState(true);
//                }
//            });
//        });
//        return images;
//    }
//
//    //private String uploadFolder = "C:/workspace/springbootwork/upload/";
//    //이것도 가능하지만 다른곳에서도 업로드 경로가 사용되면 매번 바꿀수 없으니깐 아래 방법으로 사용하는것이 좋음
//    @Value("${file.path}")
//    private String uploadFolder; //롬복아닌 org로 yml에 있는 path값 챙겨오기
//
//    @Transactional
//    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
//        UUID uuid =UUID.randomUUID();
//        String imageFileName =uuid+"_"+ imageUploadDto.getFile().getOriginalFilename();
//        //System.out.println("이미지파일이름"+imageFileName);
//        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
//
//        //통신,i/o ->예외가 발생 할 수 있어서 예외처리 필요함
//        try{
//            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //image 테이블에 저장
//        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
//        Image imageEntity = imageRepository.save(image);
//        //System.out.println(imageEntity.toString());
//    }
//}
