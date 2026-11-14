// 影视作品视频预览功能

console.log('========== Action.js 已加载 ==========');
const actionItems = document.querySelectorAll('.action-item');
console.log('找到的卡片数量:', actionItems.length);

actionItems.forEach((item, index) => {
    const videoPreview = item.querySelector('.video-preview');
    const previewVideo = item.querySelector('.preview-video');
    const playButton = item.querySelector('.play-button');
    const videoPath = item.getAttribute('data-video');

    console.log(`卡片 ${index + 1}:`, videoPath);

    // 如果没有视频路径，隐藏预览功能
    if (!videoPath) {
        if (videoPreview) {
            videoPreview.style.display = 'none';
        }
        return;
    }

    // 鼠标悬停时播放视频
    item.addEventListener('mouseenter', () => {
        if (previewVideo) {
            console.log('鼠标悬停，开始播放视频');

            // 显示视频预览区域
            videoPreview.style.opacity = '1';
            playButton.style.opacity = '0';

            // 重置并播放视频
            previewVideo.currentTime = 0;

            // 确保视频已加载
            previewVideo.load();

            // 播放视频（处理浏览器自动播放限制）
            const playPromise = previewVideo.play();

            if (playPromise !== undefined) {
                playPromise.then(() => {
                    console.log('视频播放成功');
                }).catch(error => {
                    console.error('视频播放失败:', error);
                    console.error('错误详情:', error.message);
                });
            }
        }
    });

    // 鼠标离开时暂停视频
    item.addEventListener('mouseleave', () => {
        if (previewVideo) {
            console.log('鼠标离开，暂停视频');

            // 隐藏视频预览区域
            videoPreview.style.opacity = '0';
            playButton.style.opacity = '1';

            // 暂停视频
            previewVideo.pause();
            previewVideo.currentTime = 0;
        }
    });

    // 视频加载完成后显示播放按钮
    if (previewVideo) {
        previewVideo.addEventListener('loadedmetadata', () => {
            console.log('视频元数据加载成功:', videoPath);
            console.log('视频时长:', previewVideo.duration);
            console.log('视频尺寸:', previewVideo.videoWidth, 'x', previewVideo.videoHeight);
        });

        previewVideo.addEventListener('error', (e) => {
            console.error('视频加载失败:', videoPath);
            console.error('错误事件:', e);
            console.error('视频元素错误代码:', previewVideo.error);
            console.error('错误消息:', previewVideo.error ? previewVideo.error.message : '未知错误');

            // 如果视频加载失败，隐藏预览功能
            videoPreview.style.display = 'none';
        });

        // 监听视频状态变化
        previewVideo.addEventListener('playing', () => {
            console.log('视频正在播放');
        });

        previewVideo.addEventListener('pause', () => {
            console.log('视频已暂停');
        });

        previewVideo.addEventListener('waiting', () => {
            console.log('视频缓冲中...');
        });
    }
});

// 添加键盘无障碍支持
actionItems.forEach(item => {
    item.setAttribute('tabindex', '0');
    item.setAttribute('role', 'button');
    item.setAttribute('aria-label', '预览视频');

    item.addEventListener('keydown', (e) => {
        if (e.key === 'Enter' || e.key === ' ') {
            e.preventDefault();
            item.dispatchEvent(new Event('mouseenter'));
        }
    });

    item.addEventListener('blur', () => {
        item.dispatchEvent(new Event('mouseleave'));
    });
});
