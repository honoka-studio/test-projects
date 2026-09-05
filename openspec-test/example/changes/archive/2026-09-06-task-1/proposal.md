## Why

本项目通过 `global-rules/` 与 `skills/` 分发供 AI Agent 使用的全局规则与技能，但这些文件必须被链接到用户主目录下的各 Agent 配置目录（`~/.codex`、`~/.dsh`、`~/.claude`、`~/.agents/skills`、`~/.claude/skills`）才会被相应 Agent 加载。当前缺少一套可重复执行的安装/卸载脚本，手动维护符号链接容易遗漏或残留，因此需要提供可在 Windows Git Bash 中运行的脚本，统一完成符号链接的创建与清理。

## What Changes

- 新增 `scripts/install.sh`：在用户主目录下创建全局规则与技能的符号链接，并将成功安装的技能目录名写入 `skills/installed.txt`。
- 新增 `scripts/uninstall.sh`：删除由安装流程创建的符号链接与技能目录/链接，并删除 `skills/installed.txt`。

## Capabilities

### New Capabilities

- `installer`: 通过符号链接在用户主目录下安装与卸载本项目的全局规则（`AGENTS.md` / `CLAUDE.md`）与技能目录，并维护已安装技能清单 `skills/installed.txt`。

### Modified Capabilities

<!-- 无既有能力需修改 -->

## Impact

- 新增 `scripts/install.sh` 与 `scripts/uninstall.sh`（Bash，运行于 Windows Git Bash / MSYS）。
- 读写用户主目录下的 `~/.codex/AGENTS.md`、`~/.dsh/AGENTS.md`、`~/.claude/CLAUDE.md`、`~/.agents/skills/*`、`~/.claude/skills/*`。
- 读写项目内的 `skills/installed.txt`。
- 依赖 Windows 原生符号链接（需开发者模式或管理员权限）。
