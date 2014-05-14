package com.tyr.game.screen.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.tyr.game.screen.EndScreen;
import com.tyr.game.screen.GameScreen;
import com.tyr.game.screen.MenuScreen;
import com.tyr.game.screen.SplashScreen;

public class ScreenHelper {
	
	private static ColorNode buildColor(final Element element) {
		final float red = Float.valueOf(element.getElementsByTagName("r").item(0).getTextContent());
		final float green = Float.valueOf(element.getElementsByTagName("g").item(0).getTextContent());
		final float blue = Float.valueOf(element.getElementsByTagName("b").item(0).getTextContent());
		final float alpha = Float.valueOf(element.getElementsByTagName("a").item(0).getTextContent());
		
		return new ColorNode(red, green, blue, alpha);
	}
	
	private static DurationNode buildDuration(final Element element) {
		final float display = Float.valueOf(element
				.getElementsByTagName("display").item(0).getTextContent());
		final float fade = Float.valueOf(element
				.getElementsByTagName("fade").item(0).getTextContent());
		
		return new DurationNode(display, fade);
	}

	private static Screen buildEnd(final Element element) {
		final String transition = element.getElementsByTagName("transition")
				.item(0).getTextContent();
		return new EndScreen(transition);
	}

	private static FontNode buildFont(final Element element) {
		final String name = element.getElementsByTagName("name").item(0).getTextContent();
		final int size = Integer.valueOf(element.getElementsByTagName("size").item(0).getTextContent());
		final Element colorElement = (Element) element.getElementsByTagName("color").item(0);
		final ColorNode color = buildColor(colorElement);
		
		return new FontNode(name, size, color);
	}
	
	private static Screen buildGame(final Element ELEMENT) {
		return new GameScreen();
	}
	
	private static LabelNode buildLabel(final Element element) {
		final String text = element.getElementsByTagName("text").item(0).getTextContent();
		final Element fontElement = (Element) element.getElementsByTagName("font").item(0);
		final FontNode font = buildFont(fontElement);
		
		return new LabelNode(text, font);
	}
	
	private static Screen buildMenu(final Element element) { 
		final Element headingElement = (Element) element.getElementsByTagName("heading").item(0);
		final LabelNode heading = buildLabel(headingElement);
		
		final Element backgroundTextureElement = (Element) element.getElementsByTagName("background-texture").item(0);
		final TextureNode backgroundTexture = buildTexture(backgroundTextureElement);
		
		final Element transitionButtonList = (Element) element.getElementsByTagName("transition-button-list").item(0);
		final ArrayList<TransitionButtonNode> transitionButtons = buildTransitionButtons(transitionButtonList);
		
		return new MenuScreen(heading, backgroundTexture, transitionButtons);
	}
	
	// TODO: Possibly switch to libgdx XmlReader
	public static Screen buildScreen(final String id) {
		try {
			final FileHandle screenXML = Gdx.files.internal("xml/screen.xml");
			final DocumentBuilder docBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			// Note: MUST use .read() here, not .file(), as we link from the android assets!
			final Document screenDoc = docBuilder.parse(screenXML.read());
			screenDoc.getDocumentElement().normalize();
			final NodeList screenElements = screenDoc
					.getElementsByTagName("screen");

			for (int temp = 0; temp < screenElements.getLength(); temp++) {
				Node node = screenElements.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					if (element.getAttribute("id").equalsIgnoreCase(id)) {
						final String screenType = element
								.getElementsByTagName("type").item(0)
								.getTextContent();

						switch (screenType) {
							case "splash":
								return buildSplash(element);
							case "menu":
								return buildMenu(element);
							case "game":
								return buildGame(element);
							case "end":
								return buildEnd(element);
							default:
								throw new RuntimeException(
										"Problem handling screen type: "
												+ screenType);
						}
					}
				}
			}
		} catch (final Exception exception) {
				exception.printStackTrace();
		}
		
		return null;
	}
	
	private static Screen buildSplash(final Element element) {
		final Element durationElement = (Element) element
				.getElementsByTagName("duration").item(0);
		final DurationNode duration = buildDuration(durationElement);

		final String transition = element.getElementsByTagName("transition")
				.item(0).getTextContent();
		
		final Element textureElement = (Element) element.getElementsByTagName("texture").item(0);
		final TextureNode texture = buildTexture(textureElement);
		
		return new SplashScreen(duration, transition, texture);
	}
	
	private static TextureNode buildTexture(final Element element) {
		final String name = element
				.getElementsByTagName("name").item(0).getTextContent();
		final int count = Integer.valueOf(element
				.getElementsByTagName("count").item(0).getTextContent());
		final String type = element
				.getElementsByTagName("type").item(0).getTextContent();

		return new TextureNode(name, count, type);
	}
	
	private static TransitionButtonNode buildTransitionButton(final Element element) {
		final String text = element.getElementsByTagName("text").item(0).getTextContent();
		final String transition = element.getElementsByTagName("transition").item(0).getTextContent();
		final Element fontElement = (Element) element.getElementsByTagName("font").item(0);
		final FontNode font = buildFont(fontElement);

		return new TransitionButtonNode(text, transition, font);
	}
	
	private static ArrayList<TransitionButtonNode> buildTransitionButtons(final Element element) {
		final ArrayList<TransitionButtonNode> transitionButtons = new ArrayList<TransitionButtonNode>();
		
		final NodeList transitionButtonElements = element.getElementsByTagName("transition-button");

		for(int i = 0; i < transitionButtonElements.getLength(); i++) {
			final Element transitionButtonElement = (Element) transitionButtonElements.item(i);
			final TransitionButtonNode transitionButton = buildTransitionButton(transitionButtonElement);
			transitionButtons.add(transitionButton);
		}
		
		return transitionButtons;
	}	
}
