---
name: Nodes Messaging
colors:
  surface: '#10131f'
  surface-dim: '#10131f'
  surface-bright: '#363847'
  surface-container-lowest: '#0a0e1a'
  surface-container-low: '#181b28'
  surface-container: '#1c1f2c'
  surface-container-high: '#262937'
  surface-container-highest: '#313442'
  on-surface: '#e0e1f4'
  on-surface-variant: '#c6c5d7'
  inverse-surface: '#e0e1f4'
  inverse-on-surface: '#2d303e'
  outline: '#8f8fa1'
  outline-variant: '#454655'
  surface-tint: '#bdc2ff'
  primary: '#bdc2ff'
  on-primary: '#000fa3'
  primary-container: '#2634c9'
  on-primary-container: '#b0b6ff'
  inverse-primary: '#3d4bdc'
  secondary: '#bdc5ee'
  on-secondary: '#262f50'
  secondary-container: '#3d4568'
  on-secondary-container: '#abb3dc'
  tertiary: '#c0c1ff'
  on-tertiary: '#232575'
  tertiary-container: '#404292'
  on-tertiary-container: '#b3b5ff'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#e0e0ff'
  primary-fixed-dim: '#bdc2ff'
  on-primary-fixed: '#000668'
  on-primary-fixed-variant: '#1f2ec5'
  secondary-fixed: '#dde1ff'
  secondary-fixed-dim: '#bdc5ee'
  on-secondary-fixed: '#11193a'
  on-secondary-fixed-variant: '#3d4568'
  tertiary-fixed: '#e1e0ff'
  tertiary-fixed-dim: '#c0c1ff'
  on-tertiary-fixed: '#0b0862'
  on-tertiary-fixed-variant: '#3b3d8d'
  background: '#10131f'
  on-background: '#e0e1f4'
  surface-variant: '#313442'
typography:
  headline-lg:
    fontFamily: Sora
    fontSize: 30px
    fontWeight: '700'
    lineHeight: 38px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Sora
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
    letterSpacing: -0.01em
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '500'
    lineHeight: 24px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '600'
    lineHeight: 16px
    letterSpacing: 0.02em
  headline-lg-mobile:
    fontFamily: Sora
    fontSize: 24px
    fontWeight: '700'
    lineHeight: 32px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  unit: 4px
  gutter: 16px
  margin-mobile: 20px
  margin-desktop: 40px
  stack-sm: 8px
  stack-md: 16px
  stack-lg: 24px
---

## Brand & Style

This design system is built on a "Soft-Modern" philosophy—a blend of **Minimalism** and **Glassmorphism**. The aesthetic prioritizes clarity and high-quality whitespace to reduce cognitive load during messaging. The interface feels weightless, using subtle depth through soft shadows and blurred surfaces rather than harsh lines.

The target audience is the design-conscious digital native who values both speed and elegance. The UI should evoke a sense of **calm, fluidity, and precision**. Key visual hallmarks include rounded corners, floating interface elements that appear to hover over the canvas, and a sophisticated deep blue primary tone that provides energetic focal points.

## Colors

The palette is anchored by a **Deep Electric Blue** (Primary), used for high-intent actions, active states, and outgoing message bubbles.

- **Primary:** #2634C9 — The signature interaction color.
- **Secondary:** #9DA5CD — Used for background surfaces and neutral message bubbles to provide a soft contrast.
- **Tertiary:** #7D80D4 — Reserved for status indicators and accented UI elements.
- **Neutral:** #9799AA — A cool slate gray used for secondary text and glyphs to maintain hierarchy without visual clutter.

The system is designed for a **Native Dark Mode** where primary blue remains constant, but surfaces shift to deep obsidian tones while maintaining the soft shadow depth through subtle inner glows or elevated tonal layers.

## Typography

The system uses a pairing of **Sora** and **Inter**. Sora brings a distinct, tech-forward personality to headlines, while Inter provides industry-standard legibility for communication.

- **Headlines:** Use Sora with Bold (700) and SemiBold (600) weights with slightly tightened letter spacing for a punchy, contemporary feel.
- **Body:** Inter Medium (500) is the default for message text to ensure readability against dark backgrounds. Regular (400) is used for secondary metadata.
- **Labels:** Used for timestamps and micro-copy, always paired with high-contrast color or slightly increased letter spacing for clarity.

## Layout & Spacing

The layout utilizes a **fluid grid with safe-area consciousness**. Spacing follows a 4px base unit, with the most common increments being 8px (sm), 16px (md), and 24px (lg).

- **Mobile:** Uses a 20px side margin to give content "breathability" away from the device edge.
- **Chat Threads:** Rely on asymmetrical padding—larger on the side opposite the message bubble—to reinforce the flow of conversation.
- **Containers:** Components like the story tray or chat cards use 16px internal padding (gutter) to ensure content never feels cramped.

## Elevation & Depth

Hierarchy is established through **Ambient Shadows** and **Tonal Layering**, optimized for the dark color palette.

1. **Base Layer:** The canvas (deep slate or dark neutral tones).
2. **Floating Layer:** Cards and the navigation bar use a soft, diffused shadow (`Y: 10px, Blur: 20px, Opacity: 12%`) to appear as if they are resting just above the surface.
3. **Interactive Layer:** Active buttons and floating action buttons (FAB) use a slightly higher elevation with a more saturated shadow tinting (Primary Blue at 25% opacity).
4. **Overlays:** Modals and menus use a high-blur backdrop filter (glassmorphism) to maintain context of the underlying screen.

## Shapes

The design system is characterized by **balanced roundedness** (Level 2).

- **Standard Elements:** Used for all primary buttons, input fields, and category chips with an 8px radius.
- **Large Radius Cards:** Chat list items and profile sections use a `16px` (rounded-lg) radius, creating a friendly, organic feel.
- **Avatars:** Strictly circular (squircle-variant) with a 2px inner border to ensure separation from message bubbles.
- **Message Bubbles:** Use a distinct logic where the tail-side corner is slightly sharper (4px) while the other three corners are rounded (12px).

## Components

### Buttons & Chips
- **Primary Button:** Rounded corners (8px), solid primary blue, white text.
- **Action Chips:** Secondary color background, Medium weight text, 32px height.

### Navigation Bar
- **Floating Dock:** A rounded container positioned at the bottom of the screen. It uses a high-blur backdrop filter and contains icon-only or icon-label pairings.

### Message Bubbles
- **Outgoing:** Primary Blue background, white text.
- **Incoming:** Secondary color background, light text.
- **Shadows:** Only applied to "media" bubbles (images/videos) to give them weight; text bubbles rely on color for separation.

### Input Fields
- **Chat Input:** A single, large rounded field with an integrated "attachment" icon and "send" FAB. It should appear to hover at the bottom of the chat view.

### Lists & Cards
- **Chat Card:** No borders; separation is achieved through whitespace and subtle tonal differences. On hover/active state, a subtle scale-down effect (0.98) or a faint secondary-color highlight is used.