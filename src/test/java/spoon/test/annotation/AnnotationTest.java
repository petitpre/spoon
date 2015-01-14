package spoon.test.annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import spoon.Launcher;
import spoon.compiler.SpoonCompiler;
import spoon.reflect.declaration.CtAnnotatedElementType;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtAnnotationType;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtSimpleType;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.test.annotation.testclasses.AnnotParamTypeEnum;
import spoon.test.annotation.testclasses.AnnotParamTypes;
import spoon.test.annotation.testclasses.Bound;
import spoon.test.annotation.testclasses.Foo.InnerAnnotation;
import spoon.test.annotation.testclasses.Foo.MiddleAnnotation;
import spoon.test.annotation.testclasses.Foo.OuterAnnotation;
import spoon.test.annotation.testclasses.Main;
import spoon.test.annotation.testclasses.TestInterface;

public class AnnotationTest
{
	private Factory factory;

	@Before
	public void setUp() throws Exception
	{
		final File testDirectory = new File("./src/test/java/spoon/test/annotation/testclasses/");

		Launcher launcher = new Launcher();
		this.factory = launcher.createFactory();

		SpoonCompiler compiler = launcher.createCompiler(this.factory);
		compiler.addInputSource(testDirectory);
		compiler.build();
	}

	@Test
	public void testModelBuildingAnnotationBound() throws Exception
	{
		CtSimpleType<?> type = this.factory.Type().get("spoon.test.annotation.testclasses.Bound");
		assertEquals("Bound", type.getSimpleName());
		assertEquals(1, type.getAnnotations().size());
	}

	@Test
	public void testWritingAnnotParamArray() throws Exception
	{
		CtSimpleType<?> type = this.factory.Type().get("spoon.test.annotation.testclasses.AnnotParam");
		assertEquals("@java.lang.SuppressWarnings(value = { \"unused\" , \"rawtypes\" })" + DefaultJavaPrettyPrinter.LINE_SEPARATOR, type.getElements(new TypeFilter<>(CtAnnotation.class)).get(0).toString());
	}

	@Test
	public void testModelBuildingAnnotationBoundUsage() throws Exception
	{
		CtSimpleType<?> type = this.factory.Type().get("spoon.test.annotation.testclasses.Main");
		assertEquals("Main", type.getSimpleName());

		CtParameter<?> param = type.getElements(new TypeFilter<CtParameter<?>>(CtParameter.class)).get(0);
		assertEquals("a", param.getSimpleName());

		List<CtAnnotation<? extends Annotation>> annotations = param.getAnnotations();
		assertEquals(1, annotations.size());

		CtAnnotation<?> a = annotations.get(0);
		Bound actualAnnotation = (Bound) a.getActualAnnotation();
		assertEquals(8, actualAnnotation.max());
	}

	@Test
	public void testPersistenceProperty() throws Exception
	{
		CtSimpleType<?> type = this.factory.Type().get("spoon.test.annotation.testclasses.PersistenceProperty");
		assertEquals("PersistenceProperty", type.getSimpleName());
		assertEquals(2, type.getAnnotations().size());

		CtAnnotation<Target> a1 = type.getAnnotation(type.getFactory().Type().createReference(Target.class));
		assertNotNull(a1);

		CtAnnotation<Retention> a2 = type.getAnnotation(type.getFactory().Type().createReference(Retention.class));
		assertNotNull(a2);

		assertTrue(a1.getElementValues().containsKey("value"));
		assertTrue(a2.getElementValues().containsKey("value"));
	}

	@Test
	public void testAnnotationParameterTypes() throws Exception
	{
		CtSimpleType<?> type = this.factory.Type().get("spoon.test.annotation.testclasses.Main");

		CtMethod<?> m1 = type.getElements(new NameFilter<CtMethod<?>>("m1")).get(0);

		List<CtAnnotation<? extends Annotation>> annotations = m1.getAnnotations();
		assertEquals(1, annotations.size());

		CtAnnotation<?> a = annotations.get(0);
		AnnotParamTypes annot = (AnnotParamTypes) a.getActualAnnotation();
		assertEquals(42, annot.integer());
		assertEquals(1, annot.integers().length);
		assertEquals(42, annot.integers()[0]);
		assertEquals("Hello World!", annot.string());
		assertEquals(2, annot.strings().length);
		assertEquals("Hello", annot.strings()[0]);
		assertEquals("World", annot.strings()[1]);
		assertEquals(Integer.class, annot.clazz());
		assertEquals(2, annot.classes().length);
		assertEquals(Integer.class, annot.classes()[0]);
		assertEquals(String.class, annot.classes()[1]);
		assertEquals(true, annot.b());
		assertEquals('c', annot.c());
		assertEquals(42, annot.byt());
		assertEquals((short) 42, annot.s());
		assertEquals(42, annot.l());
		assertEquals(3.14f, annot.f(), 0f);
		assertEquals(3.14159, annot.d(), 0);
		assertEquals(AnnotParamTypeEnum.G, annot.e());
		assertEquals("dd", annot.ia().value());

		CtMethod<?> m2 = type.getElements(new NameFilter<CtMethod<?>>("m2")).get(0);

		annotations = m2.getAnnotations();
		assertEquals(1, annotations.size());

		a = annotations.get(0);
		annot = (AnnotParamTypes) a.getActualAnnotation();
		assertEquals(42, annot.integer());
		assertEquals(1, annot.integers().length);
		assertEquals(42, annot.integers()[0]);
		assertEquals("Hello World!", annot.string());
		assertEquals(2, annot.strings().length);
		assertEquals("Hello", annot.strings()[0]);
		assertEquals("world", annot.strings()[1]);
		assertEquals(false, annot.b());
		assertEquals(42, annot.byt());
		assertEquals((short) 42, annot.s());
		assertEquals(42, annot.l());
		assertEquals(3.14f, annot.f(), 0f);
		assertEquals(3.14159, annot.d(), 0);
		assertEquals(AnnotParamTypeEnum.G, annot.e());
		assertEquals("dd", annot.ia().value());

		// tests binary expressions
		CtMethod<?> m3 = type.getElements(new NameFilter<CtMethod<?>>("m3")).get(0);

		annotations = m3.getAnnotations();
		assertEquals(1, annotations.size());

		a = annotations.get(0);
		annot = (AnnotParamTypes) a.getActualAnnotation();
		assertEquals(45, annot.integer());
		assertEquals(2, annot.integers().length);
		assertEquals(40, annot.integers()[0]);
		assertEquals(42*3, annot.integers()[1]);
		assertEquals("Hello World!concatenated", annot.string());
		assertEquals(2, annot.strings().length);
		assertEquals("Helloconcatenated", annot.strings()[0]);
		assertEquals("worldconcatenated", annot.strings()[1]);
		assertEquals(true, annot.b());
		assertEquals(42^1, annot.byt());
		assertEquals((short) 42 / 2, annot.s());
		assertEquals(43, annot.l());
		assertEquals(3.14f * 2f, annot.f(), 0f);
		assertEquals(3.14159d / 3d, annot.d(), 0);
		assertEquals(AnnotParamTypeEnum.G, annot.e());
		assertEquals("dddd", annot.ia().value());
	}

	@Test
	public void testAnnotatedElementTypes() throws Exception
	{
		// load package of the test classes
		CtPackage pkg = this.factory.Package().get("spoon.test.annotation.testclasses");

		// check annotated element type of the package annotation
		List<CtAnnotation<?>> annotations = pkg.getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(pkg));
		assertEquals(CtAnnotatedElementType.PACKAGE, annotations.get(0).getAnnotatedElementType());

		// load class Main from package and check annotated element type of the class annotation
		CtClass<?> clazz = pkg.getType("Main");
		assertEquals(Main.class, clazz.getActualClass());

		annotations = clazz.getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(clazz));
		assertEquals(CtAnnotatedElementType.TYPE, clazz.getAnnotations().get(0).getAnnotatedElementType());

		// load method toString() from class and check annotated element type of the annotation
		List<CtMethod<?>> methods = clazz.getMethodsByName("toString");
		assertEquals(1, methods.size());

		CtMethod<?> method = methods.get(0);
		assertEquals("toString", method.getSimpleName());

		annotations = method.getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(method));
		assertEquals(CtAnnotatedElementType.METHOD, annotations.get(0).getAnnotatedElementType());

		// load parameter of method m(int) and check annotated element type of the parameter annotation
		methods = clazz.getMethodsByName("m");
		assertEquals(1, methods.size());

		method = methods.get(0);
		assertEquals("m", method.getSimpleName());

		List<CtParameter<?>> parameters = method.getParameters();
		assertEquals(1, parameters.size());

		CtParameter<?> parameter = parameters.get(0);
		annotations = parameter.getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(parameter));
		assertEquals(CtAnnotatedElementType.PARAMETER, annotations.get(0).getAnnotatedElementType());

		// load constructor of the clazz and check annotated element type of the constructor annotation
		Set<? extends CtConstructor<?>> constructors = clazz.getConstructors();
		assertEquals(1, constructors.size());

		CtConstructor<?> constructor = constructors.iterator().next();
		annotations = constructor.getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(constructor));
		assertEquals(CtAnnotatedElementType.CONSTRUCTOR, annotations.get(0).getAnnotatedElementType());

		// load value ia of the m1() method annotation, which is also an annotation
		// and check the annotated element type of the inner annotation.
		methods = clazz.getMethodsByName("m1");
		assertEquals(1, methods.size());

		method = methods.get(0);
		annotations = method.getAnnotations();
		assertEquals(1, annotations.size());

		CtAnnotation<?> annotation = annotations.get(0);
		assertTrue(annotations.get(0).getAnnotatedElement().equals(method));
		assertEquals(CtAnnotatedElementType.METHOD, annotations.get(0).getAnnotatedElementType());

		Object element = annotation.getElementValues().get("ia");
		assertNotNull(element);
		assertTrue(element instanceof CtAnnotation);
		assertTrue(((CtAnnotation<?>) element).getAnnotatedElement().equals(annotation));
		assertEquals(CtAnnotatedElementType.ANNOTATION_TYPE, ((CtAnnotation<?>) element).getAnnotatedElementType());

		// load enum AnnotParamTypeEnum and check the annotated element type of the annotation of the enum and of the fields
		CtEnum<?> enumeration = pkg.getType("AnnotParamTypeEnum");
		assertEquals(AnnotParamTypeEnum.class, enumeration.getActualClass());

		annotations = enumeration.getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(enumeration));
		assertEquals(CtAnnotatedElementType.TYPE, annotations.get(0).getAnnotatedElementType());

		List<CtField<?>> fields = enumeration.getValues();
		assertEquals(3, fields.size());

		annotations = fields.get(0).getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(fields.get(0)));
		assertEquals(CtAnnotatedElementType.FIELD, annotations.get(0).getAnnotatedElementType());

		// load interface type TestInterface and check the annotated element type of the annotation
		CtInterface<?> ctInterface = pkg.getType("TestInterface");
		assertEquals(TestInterface.class, ctInterface.getActualClass());

		annotations = ctInterface.getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(ctInterface));
		assertEquals(CtAnnotatedElementType.TYPE, annotations.get(0).getAnnotatedElementType());

		// load annotation type Bound and check the annotated element type of the annotations
		CtAnnotationType<?> annotationType = pkg.getType("Bound");
		assertEquals(Bound.class, annotationType.getActualClass());

		annotations = annotationType.getAnnotations();
		assertEquals(1, annotations.size());
		assertTrue(annotations.get(0).getAnnotatedElement().equals(annotationType));
		assertEquals(CtAnnotatedElementType.ANNOTATION_TYPE, annotations.get(0).getAnnotatedElementType());
	}

	@Test
	public void testAnnotationWithDefaultArrayValue() throws  Throwable{
		final String res = "java.lang.Class<?>[] value() default {  };";

		CtSimpleType<?> type = this.factory.Type().get("spoon.test.annotation.testclasses.AnnotArrayInnerClass");
		CtSimpleType<?> annotationInnerClass = type.getNestedType("Annotation");
		assertEquals("Annotation", annotationInnerClass.getSimpleName());
		assertEquals(1, annotationInnerClass.getAnnotations().size());
		assertEquals(res, annotationInnerClass.getField("value").toString());

		CtSimpleType<?> annotation = this.factory.Type().get("spoon.test.annotation.testclasses.AnnotArray");
		assertEquals("AnnotArray", annotation.getSimpleName());
		assertEquals(1, annotation.getAnnotations().size());
		assertEquals(res, annotation.getField("value").toString());
	}

	@Test
	public void testInnerAnnotationsWithArray() throws Exception {
		final CtClass<?> ctClass = (CtClass<?>) this.factory.Type().get("spoon.test.annotation.testclasses.Foo");
		final CtMethod<?> testMethod = ctClass.getMethodsByName("test").get(0);
		final List<CtAnnotation<? extends Annotation>> testMethodAnnotations = testMethod.getAnnotations();
		assertEquals(1, testMethodAnnotations.size());

		final CtAnnotation<? extends Annotation> firstAnnotation = testMethodAnnotations.get(0);
		assertEquals(OuterAnnotation.class, getActualClassFromAnnotation(firstAnnotation));

		final CtNewArray<?> arrayAnnotations = (CtNewArray) firstAnnotation.getElementValues().get("value");
		assertEquals(2, arrayAnnotations.getElements().size());

		final CtAnnotation<?> firstAnnotationInArray = getMiddleAnnotation(arrayAnnotations, 0);
		assertEquals(MiddleAnnotation.class, getActualClassFromAnnotation(firstAnnotationInArray));

		final CtAnnotation<?> secondAnnotationInArray = getMiddleAnnotation(arrayAnnotations, 1);
		assertEquals(MiddleAnnotation.class, getActualClassFromAnnotation(secondAnnotationInArray));

		final CtAnnotation<?> innerAnnotationInFirstMiddleAnnotation = getInnerAnnotation(firstAnnotationInArray);
		assertEquals(InnerAnnotation.class, getActualClassFromAnnotation(innerAnnotationInFirstMiddleAnnotation));
		assertEquals("hello", getLiteralValueInAnnotation(innerAnnotationInFirstMiddleAnnotation).getValue());

		final CtAnnotation<?> innerAnnotationInSecondMiddleAnnotation = getInnerAnnotation(secondAnnotationInArray);
		assertEquals(InnerAnnotation.class, getActualClassFromAnnotation(innerAnnotationInSecondMiddleAnnotation));
		assertEquals("hello again", getLiteralValueInAnnotation(innerAnnotationInSecondMiddleAnnotation).getValue());
	}

	private Class<? extends Annotation> getActualClassFromAnnotation(CtAnnotation<? extends Annotation> annotation) {
		return annotation.getAnnotationType().getActualClass();
	}

	private CtLiteral<?> getLiteralValueInAnnotation(CtAnnotation<?> annotation) {
		return (CtLiteral<?>) annotation.getElementValues().get("value");
	}

	private CtAnnotation getInnerAnnotation(CtAnnotation<?> firstAnnotationInArray) {
		return (CtAnnotation<?>) firstAnnotationInArray.getElementValues().get("value");
	}

	private CtAnnotation<?> getMiddleAnnotation(CtNewArray<?> arrayAnnotations, int index) {
		return (CtAnnotation<?>) arrayAnnotations.getElements().get(index);
	}
}
