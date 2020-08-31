package io.thebitspud.libgdxstrategy.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetLibrary extends AssetManager {
	private TextureRegion[] tiles;
	private TextureRegion[][] units;
	public TextureRegionDrawable[][] buttons;

	public Label.LabelStyle titleStyle, subTitleStyle, largeTextStyle, smallTextStyle;

	public AssetLibrary() {
		buttons = new TextureRegionDrawable[15][3];
		tiles = new TextureRegion[8];
		units = new TextureRegion[4][2];
	}

	public void loadAll() {
		loadTextures();
		loadAudio();
		generateFonts();

		finishLoading();
		assignTextures();
		assignAudio();
	}

	private void loadTextures() {
		this.load("buttons.png", Texture.class);
		this.load("tiles.png", Texture.class);
		this.load("units.png", Texture.class);
	}

	private void assignTextures() {
		final Texture tileSheet = this.get("tiles.png", Texture.class);
		final Texture unitSheet = this.get("units.png", Texture.class);
		final Texture buttonSheet = this.get("buttons.png", Texture.class);

		for(int i = 0; i < 8; i++)
			tiles[i] = new TextureRegion(tileSheet,i * 64, 0, 64, 64);

		for(int i = 0; i < 4; i++) {
			units[i][0] = new TextureRegion(unitSheet, i * 64, 0, 64, 64);
			units[i][1] = new TextureRegion(unitSheet, i * 64, 64, 64, 64);
		}

		for (int i = 0; i < 6; i++)
			buttons[i] = getButton(buttonSheet,0, i * 90, 400);

		for(int i = 6; i < 15; i++)
			buttons[i] = getButton(buttonSheet,1200, i * 90, 90);
	}

	private TextureRegionDrawable[] getButton(Texture sheet, int x, int y, int width) {
		final TextureRegion iconUp = new TextureRegion(sheet, x, y, width, 180);
		final TextureRegion iconHover = new TextureRegion(sheet, x + width, y, width, 180);
		final TextureRegion iconDown = new TextureRegion(sheet, x + width * 2, y, width, 180);

		TextureRegionDrawable[] button = new TextureRegionDrawable[3];

		button[0] = new TextureRegionDrawable(iconUp);
		button[1] = new TextureRegionDrawable(iconHover);
		button[2] = new TextureRegionDrawable(iconDown);

		return button;
	}

	private void loadAudio() {}

	private void assignAudio() {}

	private void generateFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Montserrat-Regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.incremental = true;

		parameter.size = 64;
		titleStyle = new Label.LabelStyle(generator.generateFont(parameter), Color.WHITE);

		parameter.size = 48;
		subTitleStyle = new Label.LabelStyle(generator.generateFont(parameter), Color.WHITE);

		parameter.size = 24;
		largeTextStyle = new Label.LabelStyle(generator.generateFont(parameter), Color.WHITE);

		parameter.size = 14;
		smallTextStyle = new Label.LabelStyle(generator.generateFont(parameter), Color.WHITE);

		generator.dispose();
	}
}