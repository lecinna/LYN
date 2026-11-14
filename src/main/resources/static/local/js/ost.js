/*
// 音乐播放器功能
    const audioPlayer = document.getElementById('audioPlayer');
    const playBtn = document.getElementById('playBtn');
    const playIcon = document.getElementById('playIcon');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const volumeSlider = document.getElementById('volumeSlider');
    const progressTrack = document.getElementById('progressTrack');
    const progressFill = document.getElementById('progressFill');
    const currentTimeEl = document.getElementById('currentTime');
    const totalTimeEl = document.getElementById('totalTime');
    const currentSongName = document.getElementById('currentSongName');
    const currentOperaName = document.getElementById('currentOperaName');
    const discAnimation = document.getElementById('discAnimation');

    let currentIndex = -1;
    let isPlaying = false;
    const ostItems = document.querySelectorAll('.ost-item');

    // 播放音乐
    function playMusic(index) {
        if (index < 0 || index >= ostItems.length) return;

        const item = ostItems[index];
        const musicPath = item.getAttribute('data-music');
        const songName = item.querySelector('.ost-song .value').textContent;
        const operaName = item.querySelector('.ost-opera .value').textContent;

        if (!musicPath) {
        alert('该歌曲暂无音频文件');
        return;
    }

        currentIndex = index;
        audioPlayer.src = musicPath;
        audioPlayer.play();
        isPlaying = true;

        // 更新UI
        playIcon.textContent = '';
        currentSongName.textContent = songName;
        currentOperaName.textContent = operaName;
        discAnimation.classList.add('rotating');

        // 更新播放状态
        updatePlayingState();
    }

    // 暂停音乐
    function pauseMusic() {
        audioPlayer.pause();
        isPlaying = false;
        playIcon.textContent = '▶';
        discAnimation.classList.remove('rotating');
    }

    // 切换播放/暂停
    playBtn.addEventListener('click', () => {
        if (currentIndex === -1) {
        playMusic(0);
    } else if (isPlaying) {
        pauseMusic();
    } else {
        audioPlayer.play();
        isPlaying = true;
        playIcon.textContent = '';
        discAnimation.classList.add('rotating');
    }
    });

    // 上一首
    prevBtn.addEventListener('click', () => {
        if (currentIndex > 0) {
        playMusic(currentIndex - 1);
    } else {
        playMusic(ostItems.length - 1);
    }
    });

    // 下一首
    nextBtn.addEventListener('click', () => {
        if (currentIndex < ostItems.length - 1) {
        playMusic(currentIndex + 1);
    } else {
        playMusic(0);
    }
    });

    // 点击OST列表项播放
    ostItems.forEach((item, index) => {
        item.addEventListener('click', () => {
            if (currentIndex === index && isPlaying) {
                pauseMusic();
            } else {
                playMusic(index);
            }
        });
    });

    // 更新播放状态
    function updatePlayingState() {
        ostItems.forEach((item, index) => {
            const playIcon = item.querySelector('.play-icon');
            if (index === currentIndex) {
                item.classList.add('active');
                playIcon.textContent = isPlaying ? '' : '▶';
            } else {
                item.classList.remove('active');
                playIcon.textContent = '▶';
            }
        });
    }

    // 音量控制
    volumeSlider.addEventListener('input', (e) => {
        audioPlayer.volume = e.target.value / 100;
    });

    // 进度条更新
    audioPlayer.addEventListener('timeupdate', () => {
        if (audioPlayer.duration) {
        const progress = (audioPlayer.currentTime / audioPlayer.duration) * 100;
        progressFill.style.width = progress + '%';
        currentTimeEl.textContent = formatTime(audioPlayer.currentTime);
    }
    });

    audioPlayer.addEventListener('loadedmetadata', () => {
        totalTimeEl.textContent = formatTime(audioPlayer.duration);
    });

    // 点击进度条跳转
    progressTrack.addEventListener('click', (e) => {
        const rect = progressTrack.getBoundingClientRect();
        const clickX = e.clientX - rect.left;
        const width = rect.width;
        const percent = clickX / width;
        audioPlayer.currentTime = percent * audioPlayer.duration;
    });

    // 播放结束自动下一首
    audioPlayer.addEventListener('ended', () => {
        if (currentIndex < ostItems.length - 1) {
        playMusic(currentIndex + 1);
    } else {
        playMusic(0);
    }
    });

    // 格式化时间
    function formatTime(seconds) {
        const mins = Math.floor(seconds / 60);
        const secs = Math.floor(seconds % 60);
        return mins + ':' + (secs < 10 ? '0' : '') + secs;
    }

    // 初始化音量
    audioPlayer.volume = 0.8;
*/

// ========================================
// OST 音乐播放器 - 搜索跳转版
// 点击歌曲 → 跳转到平台搜索页面
// ========================================

// ===== 获取页面元素 =====
const ostItems = document.querySelectorAll('.ost-item');
const audio = document.getElementById('audioPlayer');
const playBtn = document.getElementById('playBtn');
const playIcon = document.getElementById('playIcon');
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');
const progressFill = document.getElementById('progressFill');
const progressTrack = document.getElementById('progressTrack');
const currentTimeDisplay = document.getElementById('currentTime');
const totalTimeDisplay = document.getElementById('totalTime');
const volumeSlider = document.getElementById('volumeSlider');
const currentSongName = document.getElementById('currentSongName');
const currentOperaName = document.getElementById('currentOperaName');
const discAnimation = document.getElementById('discAnimation');

// ===== 平台搜索链接配置 =====
const PLATFORM_SEARCH = {
    netease: {
        name: '网易云音乐',
        url: 'https://music.163.com/#/search/m/?s='
    },
    tencent: {
        name: 'QQ音乐',
        url: 'https://y.qq.com/n/ryqq/search?w='
    },
    kugou: {
        name: '酷狗音乐',
        url: 'https://www.kugou.com/yy/html/search.html#searchType=song&searchKeyWord='
    }
};

let currentIndex = -1;

// ===== 跳转到搜索页面 =====
function jumpToSearch(index) {
    if (index < 0 || index >= ostItems.length) return;

    const item = ostItems[index];
    const songName = item.dataset.songName || '未知歌曲';
    const operaName = item.dataset.operaName || '';
    const source = item.dataset.source || 'kugou';

    // 更新播放器界面
    currentSongName.textContent = songName;
    currentOperaName.textContent = operaName || '选择一首歌曲开始播放';

    // 更新高亮
    ostItems.forEach(el => el.classList.remove('active'));
    item.classList.add('active');
    currentIndex = index;

    const config = PLATFORM_SEARCH[source] || PLATFORM_SEARCH.kugou;
    const searchUrl = config.url + encodeURIComponent(songName);

    // 跳转到搜索页面
    if (confirm('🎵 将跳转到 ' + config.name + ' 搜索：\n"' + songName + '"')) {
        window.open(searchUrl, '_blank');
    }
}

// ===== 上一首 =====
function playPrev() {
    if (ostItems.length === 0) return;
    let index = currentIndex - 1;
    if (index < 0) index = ostItems.length - 1;
    jumpToSearch(index);
}

// ===== 下一首 =====
function playNext() {
    if (ostItems.length === 0) return;
    let index = currentIndex + 1;
    if (index >= ostItems.length) index = 0;
    jumpToSearch(index);
}

// ===== 播放/暂停 =====
function togglePlay() {
    if (currentIndex === -1 && ostItems.length > 0) {
        jumpToSearch(0);
    } else if (currentIndex >= 0) {
        jumpToSearch(currentIndex);
    }
}

// ===== 进度条（保留UI，但实际不工作） =====
function updateProgress() {
    if (audio.duration) {
        const progress = (audio.currentTime / audio.duration) * 100;
        progressFill.style.width = progress + '%';
        currentTimeDisplay.textContent = formatTime(audio.currentTime);
        totalTimeDisplay.textContent = formatTime(audio.duration);
    } else {
        currentTimeDisplay.textContent = '0:00';
        totalTimeDisplay.textContent = '0:00';
    }
}

function formatTime(seconds) {
    if (isNaN(seconds) || !isFinite(seconds)) return '0:00';
    const min = Math.floor(seconds / 60);
    const sec = Math.floor(seconds % 60);
    return min + ':' + (sec < 10 ? '0' : '') + sec;
}

// ===== 列表点击事件 =====
ostItems.forEach((item, index) => {
    const playBtn = item.querySelector('.ost-play-btn');
    if (playBtn) {
        playBtn.addEventListener('click', function(e) {
            e.stopPropagation();
            jumpToSearch(index);
        });
    }

    item.addEventListener('dblclick', function() {
        jumpToSearch(index);
    });
});

// ===== 控件事件 =====
playBtn.addEventListener('click', togglePlay);
prevBtn.addEventListener('click', playPrev);
nextBtn.addEventListener('click', playNext);

volumeSlider.addEventListener('input', function() {
    audio.volume = this.value / 100;
});

progressTrack.addEventListener('click', function(e) {
    if (audio.duration) {
        const rect = progressTrack.getBoundingClientRect();
        const percent = (e.clientX - rect.left) / rect.width;
        audio.currentTime = percent * audio.duration;
    }
});

audio.addEventListener('timeupdate', updateProgress);
audio.addEventListener('loadedmetadata', function() {
    totalTimeDisplay.textContent = formatTime(audio.duration);
});

// ===== 键盘快捷键 =====
document.addEventListener('keydown', function(e) {
    if (e.target.tagName === 'INPUT' || e.target.tagName === 'TEXTAREA') return;
    if (e.code === 'Space') {
        e.preventDefault();
        togglePlay();
    } else if (e.code === 'ArrowRight') {
        e.preventDefault();
        playNext();
    } else if (e.code === 'ArrowLeft') {
        e.preventDefault();
        playPrev();
    }
});

// ===== 初始化 =====
console.log('🎵 OST 已加载，共 ' + ostItems.length + ' 首歌曲');
console.log('📌 点击歌曲 → 跳转到对应平台搜索');