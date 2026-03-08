import { useState, useRef } from "react";

export default function SharvamAudioPlayer() {
  const [status, setStatus] = useState("idle");
  const audioRef = useRef(null);
  const audioUrlRef = useRef(null);

  const handlePlay = async () => {
    if (status === "playing") {
      audioRef.current.pause();
      audioRef.current.currentTime = 0;
      setStatus("idle");
      return;
    }

    if (audioUrlRef.current) {
      URL.revokeObjectURL(audioUrlRef.current);
      audioUrlRef.current = null;
    }

    setStatus("loading");

    try {
      const response = await fetch("http://localhost:8085/wise/sharvam");
      if (!response.ok) throw new Error();

      const blob = await response.blob();
      const url = URL.createObjectURL(new Blob([blob], { type: "audio/wav" }));
      audioUrlRef.current = url;

      audioRef.current.src = url;
      audioRef.current.onended = () => setStatus("idle");
      await audioRef.current.play();
      setStatus("playing");
    } catch {
      setStatus("error");
      setTimeout(() => setStatus("idle"), 2000);
    }
  };

  const label = {
    idle: "▶  Play",
    loading: "⏳  Loading...",
    playing: "⏹  Stop",
    error: "✕  Error",
  }[status];

  return (
    <div style={{
      display: "flex", alignItems: "center", justifyContent: "center",
      minHeight: "100vh", background: "#111"
    }}>
      <button
        onClick={handlePlay}
        disabled={status === "loading"}
        style={{
          padding: "16px 40px",
          fontSize: "18px",
          fontFamily: "monospace",
          background: status === "playing" ? "#ef4444"
                    : status === "error"   ? "#f59e0b"
                    : "#22c55e",
          color: "#fff",
          border: "none",
          borderRadius: "10px",
          cursor: status === "loading" ? "not-allowed" : "pointer",
          opacity: status === "loading" ? 0.7 : 1,
          transition: "background 0.2s, transform 0.1s",
        }}
      >
        {label}
      </button>
      <audio ref={audioRef} style={{ display: "none" }} />
    </div>
  );
}