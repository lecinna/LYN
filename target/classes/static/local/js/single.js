// 单曲播放功能

console.log('========== Single.js 已加载 ==========');

const audioPlayer = document.getElementById('audioPlayer');
const songRows = document.querySelectorAll('.song-row');
let currentIndex = -1;
let isPlaying = false;

songRows.forEach((row, index) => {
    const playIcon = row.querySelector('.play-icon');
    const musicPath = row.getAttribute('data-music');
    const songName = row.querySelector('.song-name').textContent;

    console.log(`歌曲 ${index + 1}: ${songName}`, musicPath);

    // 如果没有音乐路径，隐藏播放按钮
    if (!musicPath) {
        if (playIcon) {
            playIcon.style.opacity = '0';
            playIcon.style.pointerEvents = 'none';
        }
        return;
    }

    // 点击歌曲行播放
    row.addEventListener('click', (e) => {
        e.preventDefault();

        if (currentIndex === index && isPlaying) {
            // 当前歌曲正在播放，点击则暂停
            pauseSong();
        } else {
            // 播放新歌曲
            playSong(index, musicPath, songName);
        }
    });

    // 播放歌曲
    function playSong(index, musicPath, songName) {
        console.log('播放歌曲:', songName, '路径:', musicPath);

        // 重置上一首的状态
        resetAllRows();

        currentIndex = index;
        audioPlayer.src = musicPath;

        const playPromise = audioPlayer.play();

        if (playPromise !== undefined) {
            playPromise.then(() => {
                console.log('播放成功:', songName);
                isPlaying = true;
                updatePlayIcon(row, true);
                row.classList.add('playing');
            }).catch(error => {
                console.error('播放失败:', error.message);
                alert('该歌曲暂无音频文件或播放失败');
            });
        }
    }

    // 暂停歌曲
    function pauseSong() {
        console.log('暂停播放');
        audioPlayer.pause();
        isPlaying = false;
        updatePlayIcon(row, false);
        row.classList.remove('playing');
    }

    // 更新播放图标
    function updatePlayIcon(row, playing) {
        const icon = row.querySelector('.play-icon');
        if (icon) {
            icon.textContent = playing ? '' : '▶';
        }
    }

    // 重置所有行的状态
    function resetAllRows() {
        songRows.forEach(r => {
            r.classList.remove('playing');
            const icon = r.querySelector('.play-icon');
            if (icon) {
                icon.textContent = '▶';
            }
        });
    }
});

// 播放结束自动停止
audioPlayer.addEventListener('ended', () => {
    console.log('播放结束');
    isPlaying = false;
    resetAllRows();
});

// 播放错误处理
audioPlayer.addEventListener('error', (e) => {
    console.error('音频加载失败:', e);
    console.error('当前音频源:', audioPlayer.src);
    isPlaying = false;
    resetAllRows();
});

// 键盘支持
songRows.forEach(row => {
    row.setAttribute('tabindex', '0');
    row.setAttribute('role', 'button');

    row.addEventListener('keydown', (e) => {
        if (e.key === 'Enter' || e.key === ' ') {
            e.preventDefault();
            row.click();
        }
    });
});

function resetAllRows() {
    songRows.forEach(r => {
        r.classList.remove('playing');
        const icon = r.querySelector('.play-icon');
        if (icon) {
            icon.textContent = '▶';
        }
    });
}