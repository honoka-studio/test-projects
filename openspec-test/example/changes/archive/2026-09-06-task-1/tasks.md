## 1. Uninstall script

- [x] 1.1 实现 `scripts/uninstall.sh` 的全局规则清理：当 `~/.codex/AGENTS.md`、`~/.dsh/AGENTS.md` 是指向 `[proj]/global-rules/_AGENTS.md` 的符号链接、且 `~/.claude/CLAUDE.md` 是指向 `[proj]/global-rules/_CLAUDE.md` 的符号链接时删除它们。验证：在 Git Bash 运行 `bash scripts/uninstall.sh`，非指向本项目文件的同名条目被保留。
- [x] 1.2 实现技能目录名收集：读取 `[proj]/skills` 下所有一级子目录名，并在 `[proj]/skills/installed.txt` 存在时合并其中的名字（去除空行与回车符）。验证：构造含已删除技能的 `installed.txt`，确认卸载时该名字被纳入处理。
- [x] 1.3 实现按名称删除技能条目并清理清单：对收集到的每个名字删除 `~/.agents/skills/<name>` 与 `~/.claude/skills/<name>` 下的同名目录或符号链接，最后删除 `[proj]/skills/installed.txt`。验证：运行后上述两目录中不再存在同名条目，且 `installed.txt` 已不存在。

## 2. Install script

- [x] 2.1 实现 `scripts/install.sh` 的通用链接函数：在 MSYS/MinGW 下设置 `MSYS=winsymlinks:nativestrict`，创建链接前若目标位置已存在同名文件/目录/符号链接（含损坏链接）则先删除。验证：对一个已存在同名文件的目标位置运行，得到指向正确 target 的符号链接。
- [x] 2.2 实现安装入口：`install.sh` 第一步调用 `./scripts/uninstall.sh`，随后创建 `~/.codex/AGENTS.md`、`~/.dsh/AGENTS.md`、`~/.claude/CLAUDE.md` 三个符号链接。验证：运行后三个链接分别指向 `[proj]/global-rules/_AGENTS.md` 与 `_CLAUDE.md`。
- [x] 2.3 实现技能链接创建与清单写入：为 `[proj]/skills` 的每个一级子目录在 `~/.agents/skills` 与 `~/.claude/skills` 下建立符号链接，并将成功安装的名字逐行写入 `[proj]/skills/installed.txt`。验证：运行后两目录下存在对应链接，`installed.txt` 内容与技能目录名一致。
