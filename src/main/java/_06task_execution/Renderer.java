package _06task_execution;

import java.util.List;
import java.util.concurrent.*;

import static _05building_blocks.LaunderThrowable.launderThrowable;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/3
 */
public abstract class Renderer {
    private final ExecutorService executor;

    public Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
        for (final ImageInfo imageInfo : imageInfos) {
            completionService.submit(imageInfo::downloadImage);
        }
        renderText(source);
        try {
            for (int i = 0; i < imageInfos.size(); i++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }
    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);

}
