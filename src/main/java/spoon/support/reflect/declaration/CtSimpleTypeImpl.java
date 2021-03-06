/*
 * Spoon - http://spoon.gforge.inria.fr/
 * Copyright (C) 2006 INRIA Futurs <renaud.pawlak@inria.fr>
 *
 * This software is governed by the CeCILL-C License under French law and
 * abiding by the rules of distribution of free software. You can use, modify
 * and/or redistribute the software under the terms of the CeCILL-C license as
 * circulated by CEA, CNRS and INRIA at http://www.cecill.info.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the CeCILL-C License for more details.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 */

package spoon.support.reflect.declaration;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import spoon.reflect.declaration.CtAnnotationType;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtSimpleType;
import spoon.reflect.reference.CtArrayTypeReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.CtScanner;
import spoon.reflect.visitor.Query;
import spoon.reflect.visitor.filter.ReferenceTypeFilter;
import spoon.support.compiler.SnippetCompilationHelper;

public abstract class CtSimpleTypeImpl<T> extends CtNamedElementImpl implements
		CtSimpleType<T> {

	private static final long serialVersionUID = 1L;

	public <F> boolean addField(CtField<F> field) {
		if (!this.fields.contains(field)) {
			return this.fields.add(field);
		}

		// field already exists
		return false;
	}

	public <F> boolean removeField(CtField<F> field) {
		return this.fields.remove(field);
	}

	public <N> boolean addNestedType(CtSimpleType<N> nestedType) {
		return this.nestedTypes.add(nestedType);
	}

	public <N> boolean removeNestedType(CtSimpleType<N> nestedType) {
		return this.nestedTypes.remove(nestedType);
	}

	public Set<CtTypeReference<?>> getUsedTypes(boolean includeSamePackage) {
		Set<CtTypeReference<?>> typeRefs = new HashSet<CtTypeReference<?>>();
		for (CtTypeReference<?> typeRef : Query.getReferences(this,
				new ReferenceTypeFilter<CtTypeReference<?>>(
						CtTypeReference.class))) {
			if (!(typeRef.isPrimitive()
					|| (typeRef instanceof CtArrayTypeReference)
					|| typeRef.toString()
							.equals(CtTypeReference.NULL_TYPE_NAME) || ((typeRef
					.getPackage() != null) && "java.lang".equals(typeRef
					.getPackage().toString())))
					&& !(!includeSamePackage && typeRef.getPackage().equals(
							this.getPackage().getReference()))) {
				typeRefs.add(typeRef);
			}
		}
		return typeRefs;
	}

	private List<CtField<?>> fields = new ArrayList<CtField<?>>();

	Set<CtSimpleType<?>> nestedTypes = new TreeSet<CtSimpleType<?>>();

	public Class<T> getActualClass() {
		return getFactory().Type().createReference(this).getActualClass();
	}

	public CtSimpleType<?> getDeclaringType() {
		if(parent == null) {
			setRootElement(true);
		}
		return getParent(CtSimpleType.class);
	}

	public CtField<?> getField(String name) {
		for (CtField<?> f : fields) {
			if (f.getSimpleName().equals(name)) {
				return f;
			}
		}
		return null;
	}

	public List<CtField<?>> getFields() {
		return fields;
	}

	@SuppressWarnings("unchecked")
	public <N extends CtSimpleType<?>> N getNestedType(final String name) {
		class NestedTypeScanner extends CtScanner {
			CtSimpleType<?> type;

			public void checkType(CtSimpleType<?> type) {
				if (type.getSimpleName().equals(name)
						&& CtSimpleTypeImpl.this
								.equals(type.getDeclaringType())) {
					this.type = type;
				}
			}

			@Override
			public <U> void visitCtClass(
					spoon.reflect.declaration.CtClass<U> ctClass) {
				scan(ctClass.getNestedTypes());
				scan(ctClass.getConstructors());
				scan(ctClass.getMethods());

				checkType(ctClass);
			}

			@Override
			public <U> void visitCtInterface(
					spoon.reflect.declaration.CtInterface<U> intrface) {
				scan(intrface.getNestedTypes());
				scan(intrface.getMethods());

				checkType(intrface);
			}

			@Override
			public <U extends java.lang.Enum<?>> void visitCtEnum(
					spoon.reflect.declaration.CtEnum<U> ctEnum) {
				scan(ctEnum.getNestedTypes());
				scan(ctEnum.getConstructors());
				scan(ctEnum.getMethods());

				checkType(ctEnum);
			}

			@Override
			public <A extends Annotation> void visitCtAnnotationType(
					CtAnnotationType<A> annotationType) {
				scan(annotationType.getNestedTypes());

				checkType(annotationType);
			};

			CtSimpleType<?> getType() {
				return type;
			}
		}
		NestedTypeScanner scanner = new NestedTypeScanner();
		scanner.scan(this);
		return (N) scanner.getType();
	}

	public Set<CtSimpleType<?>> getNestedTypes() {
		return nestedTypes;
	}

	public CtPackage getPackage() {
		if (parent instanceof CtPackage) {
			return (CtPackage) parent;
		} else if (parent instanceof CtSimpleType) {
			return ((CtSimpleType<?>) parent).getPackage();
		} else {
			return null;
		}
	}

	public String getQualifiedName() {
		if ((getPackage() != null)
				&& !getPackage().getSimpleName().equals(
						CtPackage.TOP_LEVEL_PACKAGE_NAME)) {
			return getPackage().getQualifiedName() + "." + getSimpleName();
		}
		return getSimpleName();
	}

	@Override
	public CtTypeReference<T> getReference() {
		return getFactory().Type().createReference(this);
	}

	public boolean isTopLevel() {
		return (getDeclaringType() == null) && (getPackage() != null);
	}

	public void compileAndReplaceSnippets() {
		SnippetCompilationHelper.compileAndReplaceSnippetsIn(this);
	}

	@Override
	public void setParent(CtElement parentElement) {
		super.setParent(parentElement);
		if (parentElement instanceof CtPackage) {
			CtPackage pack = (CtPackage) parentElement;
			Set<CtSimpleType<?>> types = pack.getTypes();
			// TODO: define addType()
			types.add(this);
			//pack.setTypes(types);
		}
	}

}
