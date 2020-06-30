clc;clear;
videoObj = VideoReader('1.mp4');%读视频文件
nframes = get(videoObj, 'NumberOfFrames');%获取视频文件帧个数
vidWidth = videoObj.Width;
vidHeight = videoObj.Height;
fps = videoObj.FrameRate;
%------------Writer------------
out = VideoWriter('out.avi');
out.FrameRate = fps;
open(out);
%-------Preprocessing----------
imgPath = 'E:/Collection/';        % 图像库路径
imgDir  = dir([imgPath '*.png']); % 遍历所有jpg格式文件
imgWidth = 9;imgHeight = 16;
imgLogical = zeros(imgHeight,imgWidth,length(imgDir));
for i = 1:length(imgDir)          % 遍历结构体就可以一一处理图片了
    img = double(im2bw(imresize(imread([imgPath imgDir(i).name]),[imgHeight imgWidth]))); %读取每张图片
    imgLogical(:,:,i)=img;
    % imwrite(img,[imgPath imgDir(i).name]);
end

%----------Begin-----------------
for k = 1 : nframes
    disp(['Frame: ' num2str(k) '/' num2str(nframes) '(' num2str(k/nframes*100) '%)'])
	currentFrame = read(videoObj, k);%读取第i帧
    grayFrame = double(im2bw(currentFrame));%灰度化
    grayFrame = imresize(grayFrame(960/4:end-960/4,540/5:end),[960 540]);
    [r,c] = size(grayFrame);
    grayFrame = grayFrame(1:imgHeight*floor(r/imgHeight),1:imgWidth*floor(c/imgWidth));
    [r,c] = size(grayFrame);
    modified = zeros(r,c);
    for(i=1:imgHeight:r)
        for(j=1:imgWidth:c)
            maxFit = 0;index = 1;
            for(m=1:length(imgDir))
                fit = sum(sum(grayFrame(i:i+imgHeight-1,j:j+imgWidth-1)==imgLogical(:,:,m)))/imgWidth/imgHeight;
                if(fit>maxFit)
                   maxFit = fit; index = m;
                end
            end
            modified(i:i+imgHeight-1,j:j+imgWidth-1) = imgLogical(:,:,index);%锁定最佳匹配
        end
    end
%     figure,imshow(grayFrame);figure,imshow(modified);
    writeVideo(out, modified);
    % figure,imshow(grayFrame);
end

% while hasFrame(videoObj)
%     frame = readFrame(videoObj);
%     writeVideo(out, frame);
% end

close(out);

