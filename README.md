# River Raid (Java)

A from-scratch clone of the classic Atari 2600 game **River Raid**, written in pure Java with Swing/AWT — no game engine, no external libraries. This is a personal learning project focused on building solid software architecture and deepening my understanding of object-oriented design.

## About

I'm building this as a gift for my father and as a deliberate skill-building exercise. Everything is implemented incrementally from the ground up: the game loop, state management, entity system, camera, and procedural river generation are all hand-written to understand *why* each piece works the way it does.

This is a spiritual successor to a Battle City clone I completed earlier — many architectural decisions here are conscious improvements over that project (e.g. a proper state pattern instead of enum-based `if/else` chains, a shared `Entity` base class, fixed-timestep timing, and procedural world generation).

## Current Features

- **Fixed-timestep game loop** on a dedicated thread with delta accumulation and render throttling
- **State machine** (`State` / `StateManager`) for menu, play, and future pause/game-over screens
- **Entity system** with a shared abstract base (`Entity`) providing position, velocity, bounds, and collision helpers
- **Player** with smooth horizontal movement, screen-edge clamping, and a cooldown-based shooting system
- **Bullets** with lifecycle management (spawn, travel, off-screen cleanup)
- **Camera** that follows the player and converts between world and screen coordinates
- **Procedural river** using a segment-based sliding window: the river meanders via a bounded random walk, generating new segments ahead of the player and discarding those left behind — enabling an effectively infinite course with constant memory use

## Planned

- Collision with river banks (player death)
- Enemies: helicopters, jets, ships, tankers
- Fuel depots and a fuel-management system
- Bridges and level progression
- HUD (score, lives, fuel gauge)
- Explosions and animations
- Sprites and sound

## Tech

- **Java 25** (Eclipse Temurin)
- **Swing / AWT** for windowing and rendering
- **Eclipse IDE**
- No external dependencies

## Project Structure

```
src/
├── main/       Entry point
├── game/       Core: Game panel, game loop, constants, context
├── state/      State machine (menu, play, pause, game-over)
├── entity/     Entities (Entity base, Player, Bullet, and planned FuelDepot, Bridge, Explosion)
├── enemy/      Enemy types (planned)
├── map/        Procedural river (RiverGenerator, RiverSegment, World) + planned LevelManager
├── graphics/   Camera, and planned Renderer, Animation, sprite loading
├── input/      Keyboard input (polling-based)
├── ui/         HUD and menus (planned)
└── util/       Collision helpers and utilities (planned)
```

## Running

1. Clone the repository
2. Open in Eclipse (or any IDE with a JDK 21+)
3. Run `main/Main.java`

Controls:
- **←/→** — move left/right
- **Space** — shoot
- **Enter** — start (from menu)
- **Esc** — return to menu

## Status

Work in progress. The core engine and the characteristic scrolling river are functional; gameplay systems (enemies, fuel, collisions) are next.
