## Purpose

以用户主目录中的符号链接形式，提供项目（`[proj]`指当前打开的项目的根目录）全局规则与技能的幂等安装与卸载。

## ADDED Requirements

### Requirement: 以符号链接安装全局规则

installer SHALL 在用户主目录中为项目的全局规则文件创建符号链接：

- `~/.codex/AGENTS.md` 指向 `[proj]/global-rules/_AGENTS.md`
- `~/.dsh/AGENTS.md` 指向 `[proj]/global-rules/_AGENTS.md`
- `~/.claude/CLAUDE.md` 指向 `[proj]/global-rules/_CLAUDE.md`

#### Scenario: 安装 AGENTS.md

- **WHEN** installer 运行时
- **THEN** `~/.codex/AGENTS.md` 与 `~/.dsh/AGENTS.md` 是指向 `[proj]/global-rules/_AGENTS.md` 的符号链接

#### Scenario: 安装 CLAUDE.md

- **WHEN** installer 运行时
- **THEN** `~/.claude/CLAUDE.md` 是指向 `[proj]/global-rules/_CLAUDE.md` 的符号链接

### Requirement: 以符号链接安装技能目录

installer SHALL 读取 `[proj]/skills` 下所有一级子目录的名称，并为每个名称在 `~/.agents/skills` 与 `~/.claude/skills` 中各自创建一个以该名称命名的符号链接，分别指向对应的 `[proj]/skills/<name>` 目录。

#### Scenario: 将每个技能安装到两个目标位置

- **WHEN** installer 运行且 `[proj]/skills` 含有一级子目录时
- **THEN** 对于每个此类子目录 `<name>`，`~/.agents/skills/<name>` 与 `~/.claude/skills/<name>` 是指向 `[proj]/skills/<name>` 的符号链接

### Requirement: 记录成功安装的技能

installer SHALL 将已安装的技能目录名称列表写入 `[proj]/skills/installed.txt`，每行一个名称。

#### Scenario: 写入已安装技能列表

- **WHEN** installer 成功创建技能链接时
- **THEN** `[proj]/skills/installed.txt` 恰好包含已安装技能目录的名称

### Requirement: 链接前替换已存在的条目

在创建任何符号链接之前，installer SHALL 移除目标位置处同名的既有文件、目录或符号链接。

#### Scenario: 覆盖已存在的条目

- **WHEN** 目标路径已存在文件、目录或符号链接时
- **THEN** installer 在创建新的符号链接之前移除该已存在的条目

### Requirement: 安装前先卸载

installer SHALL 在创建任何符号链接之前先运行 uninstaller。

#### Scenario: 安装前清理状态

- **WHEN** installer 运行时
- **THEN** uninstaller 最先执行，在任何新的符号链接被创建之前

### Requirement: 卸载全局规则

uninstaller SHALL 仅当 `~/.codex/AGENTS.md` 与 `~/.dsh/AGENTS.md` 分别是指向 `[proj]/global-rules/_AGENTS.md` 的符号链接时才移除它们，并SHALL 仅当 `~/.claude/CLAUDE.md` 是指向 `[proj]/global-rules/_CLAUDE.md` 的符号链接时才移除它。

#### Scenario: 移除项目所属的全局规则链接

- **WHEN** uninstaller 运行且上述主目录条目之一是符号链接并指向对应的项目文件时
- **THEN** uninstaller 移除该符号链接

#### Scenario: 保持无关条目不变

- **WHEN** uninstaller 运行且某个主目录条目不是指向对应项目文件的符号链接时
- **THEN** uninstaller 保持该条目原样不动

### Requirement: 卸载技能链接

uninstaller SHALL 收集 `[proj]/skills` 的一级子目录名称，并在 `[proj]/skills/installed.txt` 存在时，将其记录的名称合并到该集合中。对于每个名称，当 `~/.agents/skills` 与 `~/.claude/skills` 中以其命名的条目为目录或符号链接时，uninstaller SHALL 移除该条目。

#### Scenario: 按名称移除技能条目

- **WHEN** uninstaller 运行且某个收集到的名称在 `~/.agents/skills` 或 `~/.claude/skills` 中存在匹配的目录或符号链接时
- **THEN** uninstaller 移除该匹配的条目

#### Scenario: 纳入先前已安装的名称

- **WHEN** `[proj]/skills/installed.txt` 存在且列出的名称不在当前一级子目录中时
- **THEN** uninstaller 同样移除与这些已记录名称相匹配的条目

### Requirement: 移除已安装技能记录

uninstaller SHALL 删除 `[proj]/skills/installed.txt`。

#### Scenario: 清理记录

- **WHEN** uninstaller 运行时
- **THEN** `[proj]/skills/installed.txt` 不再存在
