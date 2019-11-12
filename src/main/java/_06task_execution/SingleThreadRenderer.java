package _06task_execution;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/3
 */
public abstract class SingleThreadRenderer {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downLoadImage());
        }
        for (ImageData data : imageData) {
            renderImage(data);
        }
    }
    interface ImageData {

    }

    interface ImageInfo {
        ImageData downLoadImage();
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);
}
