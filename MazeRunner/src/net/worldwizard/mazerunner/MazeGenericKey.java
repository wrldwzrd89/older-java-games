package net.worldwizard.mazerunner;

public abstract class MazeGenericKey extends MazeObject {
    // Serialization
    private static final long serialVersionUID = 7998L;

    // Constructors
    protected MazeGenericKey(final String gameAppearance,
            final String editorAppearance) {
        super(false, gameAppearance, editorAppearance, false, 0, true);
    }

    // Scriptability
    @Override
    public void postMoveAction(final Inventory inv) {
        inv.addItem(this);
        final MazeRunner app = MazeRunner.getApplication();
        app.decay();
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final Inventory inv) {
        inv.addItem(this);
        final MazeRunner app = MazeRunner.getApplication();
        app.decay();
    }

    @Override
    public abstract String toString();

    @Override
    public abstract String getName();
}