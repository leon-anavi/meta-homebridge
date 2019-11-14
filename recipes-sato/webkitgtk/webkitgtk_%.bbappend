# Disable GStreamer to avoid using plugins with commercial license
EXTRA_OECMAKE_append = " -DUSE_GSTREAMER_GL=OFF "
DEPENDS_remove = "gstreamer1.0-plugins-bad"
